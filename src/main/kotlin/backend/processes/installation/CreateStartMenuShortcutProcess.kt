package backend.processes.installation

import backend.Process
import mslinks.ShellLink
import java.io.File

class CreateStartMenuShortcutProcess : Process() {

    private val exe = File("C:\\Program Files\\Hardware Monitoring Display\\hmd.exe")
    private val win = File("${System.getProperty("user.home")}\\../../ProgramData\\Microsoft\\Windows\\")
    private val lnk = File("$win\\Start Menu\\Programs\\Hardware Monitoring Display.lnk")

    override val test: Boolean = !lnk.exists()

    override fun run() {
        println("[CreateStartMenuShortcutProcess] Testing process...")
        val testRes = test
        if(!testRes) {
            throw Exception("Testing of 'CreateStartMenuShortcutProcess' was not successful!")
        }else {
            ShellLink.createLink(exe.absolutePath,lnk.absolutePath)
        }

        if(lnk.exists()) {
            println("[CreateStartMenuShortcutProcess] Lnk file successfully extracted!")
        }
    }

}