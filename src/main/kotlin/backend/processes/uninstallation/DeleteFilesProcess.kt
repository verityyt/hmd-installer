package backend.processes.uninstallation

import backend.InstallationProperties
import backend.Process
import java.io.File


class DeleteFilesProcess : Process() {

    /**
     * Installation directory of the hmd
     */
    val folder = File(InstallationProperties.instDir)

    /**
     * HMD is installed
     */
    override var test: Boolean = folder.exists()

    /**
     * Status of the process
     */
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