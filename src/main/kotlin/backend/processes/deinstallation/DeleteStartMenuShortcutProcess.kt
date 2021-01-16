package backend.processes.deinstallation

import backend.Process
import mslinks.ShellLink
import java.io.File

class DeleteStartMenuShortcutProcess : Process() {

    private val win = File("${System.getProperty("user.home")}\\../../ProgramData\\Microsoft\\Windows\\")
    private val lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")

    override val test: Boolean = lnk.exists()

    override fun run() {
        println("[DeleteStartMenuShortcutProcess] Testing process...")
        val testRes = test
        if(!testRes) {
            throw Exception("Testing of 'DeleteStartMenuShortcutProcess' was not successful!")
        }else {
            lnk.deleteRecursively()
        }

        if(!lnk.exists()) {
            println("[DeleteStartMenuShortcutProcess] Lnk file successfully extracted!")
        }
    }

}