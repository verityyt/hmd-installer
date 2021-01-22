package backend.processes.uninstallation

import backend.Process
import java.io.File


class DeleteFilesProcess : Process() {

    val folder = File("C:\\Program Files\\Hardware Monitoring Display\\")

    override var test: Boolean = folder.exists()

    override var status: Int = 0

    override fun run() {
        println("[DeleteFilesProcess] Testing process...")
        test = folder.exists()
        if (!test) {
            throw Exception("Testing of 'DeleteFilesProcess' was not successful!")
        } else {
            folder.deleteRecursively()
        }

        if (!folder.exists()) {
            println("[DeleteFilesProcess] Zip file successfully extracted!")
        }

    }

}