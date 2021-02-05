package backend.processes.installation

import backend.InstallationProperties
import backend.Logger
import backend.Process
import mslinks.ShellLink
import java.io.File

class CreateDesktopShortcutProcess : Process() {

    /**
     * Location of **hmd.exe**
     */
    private val exe = File("${InstallationProperties.instDir}hmd.exe")

    /**
     * Directory of desktop shortcut, which has to be created in the process
     */
    private val lnk = File("${System.getProperty("user.home")}\\Desktop\\Hardware Monitoring Display.lnk")

    /**
     * If link doesn't exists
     */
    override var test: Boolean = !lnk.exists()

    /**
     * Current status of the process
     */
    override var status: Int = 0

    override fun run() {

        if(InstallationProperties.desktopLnk) {
            Logger.log("Testing process...", this.javaClass)
            test = !lnk.exists()
            if(!test) {
                HMDInstaller.showError(400, "Testing of 'CreateDesktopShortcutProcess' was not successful!")
            }else {
                ShellLink.createLink(exe.absolutePath,lnk.absolutePath)
            }

            if(lnk.exists()) {
                status = 1
            }else {
                HMDInstaller.showError(400, "Process 'CreateDesktopShortcutProcess' failed")
            }
        }else {
            Logger.log("Skipping desktop shortcut", this.javaClass)
            status = 1
        }


    }

}