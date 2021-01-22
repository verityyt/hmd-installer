package backend.processes.uninstallation

import backend.Process
import java.io.File

class DeleteStartMenuShortcutProcess : Process() {

    private val win = File("${System.getProperty("user.home")}\\../../ProgramData\\Microsoft\\Windows\\")
    private val lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")

    override var test: Boolean = lnk.exists()

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