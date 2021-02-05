package backend.processes.uninstallation

import backend.InstallationProperties
import backend.Logger
import backend.Process
import frontend.Window
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
        Logger.log("Testing process...", this.javaClass)
        test = folder.exists()
        if (!test) {
            HMDInstaller.showError(400, "Testing of 'DeleteFilesProcess' was not successful!")
        } else {
            folder.deleteRecursively()
        }

        if (!folder.exists()) {
            Logger.log("Zip file successfully extracted!", this.javaClass)
        }

    }

}