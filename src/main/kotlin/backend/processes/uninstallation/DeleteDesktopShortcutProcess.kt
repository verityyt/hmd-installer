package backend.processes.uninstallation

import backend.Process
import java.io.File

class DeleteDesktopShortcutProcess : Process() {

    /**
     * Location of start menu entry
     */
    private val lnk = File("${System.getProperty("user.home")}\\Desktop\\Hardware Monitoring Display.lnk")

    /**
     * Start menu entry exists
     */
    override var test: Boolean = lnk.exists()

    /**
     * Status of the process
     */
    override var status: Int = 0

    override fun run() {
        println("[DeleteDesktopShortcutProcess] Testing process...")
        test = lnk.exists()
        if(test) {
            lnk.deleteRecursively()
        }

        if(!lnk.exists()) {
            println("[DeleteDesktopShortcutProcess] Lnk file successfully deleted!")
        }
    }

}
