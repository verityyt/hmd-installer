package backend.processes.installation

import backend.InstallationProperties
import backend.Process
import mslinks.ShellLink
import java.io.File

class CreateStartMenuShortcutProcess : Process() {

    /**
     * Location of **hmd.exe**
     */
    private val exe = File("C:\\Program Files\\Hardware Monitoring Display\\hmd.exe")

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

        if(InstallationProperties.startLnk) {
            println("[CreateStartMenuShortcutProcess] Testing process...")
            test = !lnk.exists()
            if (!test) {
                throw Exception("Testing of 'CreateStartMenuShortcutProcess' was not successful!")
            } else {
                ShellLink.createLink(exe.absolutePath, lnk.absolutePath)
            }

            if (lnk.exists()) {
                println("[CreateStartMenuShortcutProcess] Lnk file successfully extracted!")
                status = 1
            }else {
                throw Exception("Process 'CreateStartMenuShortcutProcess' failed")
            }
        }else {
            println("[CreateStartMenuShortcutProcess] Skipping desktop shortcut")
            status = 1
        }

    }

}