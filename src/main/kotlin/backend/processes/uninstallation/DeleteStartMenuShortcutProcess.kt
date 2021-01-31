package backend.processes.uninstallation

import backend.Process
import java.io.File

class DeleteStartMenuShortcutProcess : Process() {

    /**
     * Directory of the **Windows** folder in the **ProgramData** folder
     */
    private val win = File("${System.getProperty("user.home")}\\../../ProgramData\\Microsoft\\Windows\\")

    /**
     * Location of start menu entry
     */
    private val lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")

    /**
     * Start menu entry exists
     */
    override var test: Boolean = lnk.exists()

    /**
     * Status of the process
     */
    override var status: Int = 0

    override fun run() {
        println("[DeleteStartMenuShortcutProcess] Testing process...")
        test = lnk.exists()
        if(test) {
            lnk.deleteRecursively()
        }

        if(!lnk.exists()) {
            println("[DeleteStartMenuShortcutProcess] Lnk file successfully extracted!")
        }
    }

}