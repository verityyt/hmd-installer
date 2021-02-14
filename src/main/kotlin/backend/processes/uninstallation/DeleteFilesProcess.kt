package backend.processes.uninstallation

import backend.InstallationProperties
import backend.Logger
import backend.Process
import backend.UninstallationProperties
import java.io.File


class DeleteFilesProcess : Process() {

    /**
     * Installation directory of the hmd
     */
    var folder = File(UninstallationProperties.instDir)

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
        folder = File(UninstallationProperties.instDir)
        test = folder.exists()
        if (!test) {
            println("${folder.absolutePath} doesn't exists ${folder.exists()}")
            HMDInstaller.showError(400, "Testing of 'DeleteFilesProcess' was not successful!")
        } else {
            folder.deleteRecursively()
        }

        if (!folder.exists()) {
            status = 1
        }else {
            HMDInstaller.showError(400, "Process 'DeleteFilesProcess' failed")
        }

    }

}