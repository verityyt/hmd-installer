package backend.processes.installation

import backend.InstallationProperties
import backend.Process
import frontend.Window
import mslinks.ShellLink
import java.io.File
import java.lang.Exception

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

        try {
            if (InstallationProperties.desktopLnk) {
                println("[CreateDesktopShortcutProcess] Testing process...")
                test = !lnk.exists()
                if (!test) {
                    Window.drawError(400, "Testing of 'CreateDesktopShortcutProcess' was not successful!")
                } else {
                    ShellLink.createLink(exe.absolutePath, lnk.absolutePath)
                }

                if (lnk.exists()) {
                    status = 1
                } else {
                    Window.drawError(400, "Process 'CreateDesktopShortcutProcess' failed")
                }
            } else {
                println("[CreateDesktopShortcutProcess] Skipping desktop shortcut")
                status = 1
            }
        }catch (e: Exception) {
            Window.drawError(403)
        }

    }

}