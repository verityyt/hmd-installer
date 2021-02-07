package backend.processes.installation

import backend.InstallationProperties
import backend.Logger
import backend.Process
import mslinks.ShellLink
import java.io.File
import java.lang.Exception

class CreateStartMenuShortcutProcess : Process() {

    /**
     * Location of **hmd.exe**
     */
    private var exe = File("${InstallationProperties.instDir}\\hmd.exe")

    /**
     * Directory of the **Windows** folder in the **ProgramData** folder
     */
    private val win = File("${System.getProperty("user.home")}\\../../ProgramData\\Microsoft\\Windows\\")
    private var lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")

    /**
     * If link doesn't exists
     */
    override var test: Boolean = !lnk.exists()

    /**
     * Current status of the process
     */
    override var status: Int = 0

    override fun run() {

        try {
            if (InstallationProperties.startLnk) {
                Logger.log("Testing process...", this.javaClass)
                test = !lnk.exists()
                if (!test) {
                    HMDInstaller.showError(400, "Testing of 'CreateStartMenuShortcutProcess' was not successful!")
                } else {
                    exe = File("${InstallationProperties.instDir}\\hmd.exe")
                    lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")
                    ShellLink.createLink(exe.absolutePath, lnk.absolutePath)
                }

                if (lnk.exists()) {
                    Logger.log("Lnk file successfully extracted!", this.javaClass)
                    status = 1
                } else {
                    HMDInstaller.showError(400, "Process 'CreateStartMenuShortcutProcess' failed")
                }
            } else {
                Logger.log("Skipping desktop shortcut...", this.javaClass)
                status = 1
            }
        } catch (e: Exception) {
            HMDInstaller.showError(403)
        }

    }

}