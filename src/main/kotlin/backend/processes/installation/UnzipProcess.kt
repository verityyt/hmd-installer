package backend.processes.installation

import backend.InstallationProperties
import backend.Process
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
        try {
            val destDir = File(InstallationProperties.instDir)

            println("[UnzipProcess] Testing process...")
            test = file.exists()
            if (!test) {
                HMDInstaller.showError(400, "Testing of 'UnzipProcess' was not successful!")
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
                            HMDInstaller.showError(400, "Failed to create directory $newFile")
                        }
                    } else {
                        val parent = newFile.parentFile
                        if (!parent.isDirectory && !parent.mkdirs()) {
                            HMDInstaller.showError(400, "Failed to create directory $parent")
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

            if (destDir.exists() && destDir.isDirectory) {
                println("[UnzipProcess] Zip file successfully extracted!")
                status = 1
            } else {
                HMDInstaller.showError(400, "Process 'UnzipProcess' failed")
            }
        } catch (e: Exception) {
            HMDInstaller.showError(408)
        }

    }

    @Throws(IOException::class)
    fun newFile(destinationDir: File, zipEntry: ZipEntry): File {
        val destFile = File(destinationDir, zipEntry.name)
        val destDirPath = destinationDir.canonicalPath
        val destFilePath = destFile.canonicalPath
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            HMDInstaller.showError(400, "Entry is outside of the target dir: " + zipEntry.name)
        }
        return destFile
    }

}