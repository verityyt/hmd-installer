package backend.processes.installation

import backend.Process
import mslinks.ShellLink
import java.io.File

class CreateDesktopShortcutProcess : Process() {

    private val exe = File("C:\\Program Files\\Hardware Monitoring Display\\hmd.exe")
    private val lnk = File("${System.getProperty("user.home")}\\Desktop\\Hardware Monitoring Display.lnk")

    override val test: Boolean = !lnk.exists()

    override fun run() {
        println("[CreateDesktopShortcutProcess] Testing process...")
        val testRes = test
        if(!testRes) {
            throw Exception("Testing of 'CreateDesktopShortcutProcess' was not successful!")
        }else {
            ShellLink.createLink(exe.absolutePath,lnk.absolutePath)
        }

        if(lnk.exists()) {
            println("[CreateDesktopShortcutProcess] Lnk file successfully extracted!")
        }
    }

}