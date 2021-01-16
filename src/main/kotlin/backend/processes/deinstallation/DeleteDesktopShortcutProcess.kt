package backend.processes.deinstallation

import backend.Process
import mslinks.ShellLink
import java.io.File

class DeleteDesktopShortcutProcess : Process() {

    private val lnk = File("${System.getProperty("user.home")}\\Desktop\\Hardware Monitoring Display.lnk")

    override val test: Boolean = lnk.exists()

    override fun run() {
        println("[DeleteDesktopShortcutProcess] Testing process...")
        val testRes = test
        if(!testRes) {
            throw Exception("Testing of 'DeleteDesktopShortcutProcess' was not successful!")
        }else {
            lnk.deleteRecursively()
        }

        if(!lnk.exists()) {
            println("[DeleteDesktopShortcutProcess] Lnk file successfully deleted!")
        }
    }

}