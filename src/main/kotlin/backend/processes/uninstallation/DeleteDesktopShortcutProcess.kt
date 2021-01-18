package backend.processes.uninstallation

import backend.Process
import java.io.File

class DeleteDesktopShortcutProcess : Process() {

    private val lnk = File("${System.getProperty("user.home")}\\Desktop\\Hardware Monitoring Display.lnk")

    override val test: Boolean = lnk.exists()

    override fun run() {
        println("[DeleteDesktopShortcutProcess] Testing process...")
        val testRes = test
        if(testRes) {
            lnk.deleteRecursively()
        }

        if(!lnk.exists()) {
            println("[DeleteDesktopShortcutProcess] Lnk file successfully deleted!")
        }
    }

}