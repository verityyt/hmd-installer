package backend.processes.uninstallation

import backend.Process
import java.io.File

class DeleteStartMenuShortcutProcess : Process() {

    private val win = File("${System.getProperty("user.home")}\\../../ProgramData\\Microsoft\\Windows\\")
    private val lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")

    override val test: Boolean = lnk.exists()

    override fun run() {
        println("[DeleteStartMenuShortcutProcess] Testing process...")
        val testRes = test
        if(testRes) {
            lnk.deleteRecursively()
        }

        if(!lnk.exists()) {
            println("[DeleteStartMenuShortcutProcess] Lnk file successfully extracted!")
        }
    }

}