package backend.processes.deinstallation

import backend.Process
import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipInputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.io.FileOutputStream


class DeleteFilesProcess : Process() {

    val folder = File("C:\\Program Files\\Hardware Monitoring Display\\")

    override val test: Boolean = folder.exists()

    override fun run() {
        println("[DeleteFilesProcess] Testing process...")
        val testRes = test
        if (!testRes) {
            throw Exception("Testing of 'DeleteFilesProcess' was not successful!")
        } else {
            folder.deleteRecursively()
        }

        if (!folder.exists()) {
            println("[DeleteFilesProcess] Zip file successfully extracted!")
        }

    }

}