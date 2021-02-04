package backend.processes.installation

import backend.InstallationProperties
import backend.Process
import frontend.Window
import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipInputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.io.FileOutputStream

class UnzipProcess : Process() {

    /**
     * Location of the downloaded zip file
     */
    private val file = File("files\\current-version.zip")

    /**
     * If downloaded zip file exists
     */
    override var test: Boolean = file.exists()

    /**
     * Current status of the process
     */
    override var status: Int = 0

    override fun run() {
        val destDir = File(InstallationProperties.instDir)
        try {
            println("[UnzipProcess] Testing process...")
            test = file.exists()
            if (!test) {
                Window.drawError(400, "Testing of 'UnzipProcess' was not successful!")
            } else {
                println("[UnzipProcess] Extracting zip file...")
                val zipLoc = file.absolutePath

                val buffer = ByteArray(1024)
                val zipIn = ZipInputStream(FileInputStream(zipLoc))
                var zipEntry = zipIn.nextEntry
                while (zipEntry != null) {
                    val newFile = newFile(destDir, zipEntry)
                    if (zipEntry.isDirectory) {
                        if (!newFile.isDirectory && !newFile.mkdirs()) {
                            Window.drawError(400, "Failed to create directory $newFile")
                        }
                    } else {
                        val parent = newFile.parentFile
                        if (!parent.isDirectory && !parent.mkdirs()) {
                            Window.drawError(400, "Failed to create directory $newFile")
                        }

                        val fos = FileOutputStream(newFile)
                        var len: Int
                        while (zipIn.read(buffer).also { len = it } > 0) {
                            fos.write(buffer, 0, len)
                        }
                        fos.close()
                    }
                    zipEntry = zipIn.nextEntry
                }
                zipIn.closeEntry()
                zipIn.close()
            }
        } catch (e: Exception) {
            Window.drawError(403)
        }

        if (destDir.exists() && destDir.isDirectory) {
            println("[UnzipProcess] Zip file successfully extracted!")
            status = 1
        } else {
            Window.drawError(400, "Process 'UnzipProcess' failed")
        }

    }

    @Throws(IOException::class)
    fun newFile(destinationDir: File, zipEntry: ZipEntry): File {
        val destFile = File(destinationDir, zipEntry.name)
        val destDirPath = destinationDir.canonicalPath
        val destFilePath = destFile.canonicalPath
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            Window.drawError(400, "Entry is outside of the target dir: " + zipEntry.name)
        }
        return destFile
    }

}