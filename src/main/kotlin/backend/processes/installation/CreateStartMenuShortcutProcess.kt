package backend.processes.installation

import backend.InstallationProperties
import backend.Process
import frontend.Window
import mslinks.ShellLink
import java.io.File
import java.lang.Exception

class CreateStartMenuShortcutProcess : Process() {

    /**
     * Location of **hmd.exe**
     */
    private val exe = File("${InstallationProperties.instDir}hmd.exe")

    /**
     * Directory of the **Windows** folder in the **ProgramData** folder
     */
    private val win = File("${System.getProperty("user.home")}\\../../ProgramData\\Microsoft\\Windows\\")
    private val lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")

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
                println("[CreateStartMenuShortcutProcess] Testing process...")
                test = !lnk.exists()
                if (!test) {
                    Window.drawError(400, "Testing of 'CreateStartMenuShortcutProcess' was not successful!")
                } else {
                    ShellLink.createLink(exe.absolutePath, lnk.absolutePath)
                }

                if (lnk.exists()) {
                    println("[CreateStartMenuShortcutProcess] Lnk file successfully extracted!")
                    status = 1
                } else {
                    Window.drawError(400, "Process 'CreateStartMenuShortcutProcess' failed")
                }
            } else {
                println("[CreateStartMenuShortcutProcess] Skipping desktop shortcut")
                status = 1
            }
        }catch (e: Exception) {
            Window.drawError(403)
        }

    }

}