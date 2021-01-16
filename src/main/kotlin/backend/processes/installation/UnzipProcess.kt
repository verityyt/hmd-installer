package backend.processes.installation

import backend.Process
import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipInputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.io.FileOutputStream


class UnzipProcess : Process() {

    private val file = File("files\\current-version.zip")

    override val test: Boolean = file.exists()

    override fun run() {
        val destDir = File("C:\\Program Files\\Hardware Monitoring Display\\")

        println("[UnzipProcess] Testing process...")
        val testRes = test
        if (!testRes) {
            throw Exception("Testing of 'UnzipProcess' was not successful!")
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
                        throw Exception("Failed to create directory $newFile")
                    }
                } else {
                    val parent = newFile.parentFile
                    if (!parent.isDirectory && !parent.mkdirs()) {
                        throw Exception("Failed to create directory $parent")
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
        }

    }

    @Throws(IOException::class)
    fun newFile(destinationDir: File, zipEntry: ZipEntry): File {
        val destFile = File(destinationDir, zipEntry.name)
        val destDirPath = destinationDir.canonicalPath
        val destFilePath = destFile.canonicalPath
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw IOException("Entry is outside of the target dir: " + zipEntry.name)
        }
        return destFile
    }

}