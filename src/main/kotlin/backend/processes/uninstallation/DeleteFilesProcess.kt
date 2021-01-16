package backend.processes.uninstallation

import backend.Process
import java.io.File


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