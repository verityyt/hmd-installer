package backend.processes.installation

import backend.InstallationProperties
import backend.Process
import mslinks.ShellLink
import java.io.File

class CreateDesktopShortcutProcess : Process() {

    private val exe = File("C:\\Program Files\\Hardware Monitoring Display\\hmd.exe")
    private val lnk = File("${System.getProperty("user.home")}\\Desktop\\Hardware Monitoring Display.lnk")

    override var test: Boolean = !lnk.exists()

    override var status: Int = 0

    override fun run() {

        if(InstallationProperties.desktopLnk) {
            println("[CreateDesktopShortcutProcess] Testing process...")
            test = !lnk.exists()
            if(!test) {
                throw Exception("Testing of 'CreateDesktopShortcutProcess' was not successful!")
            }else {
                ShellLink.createLink(exe.absolutePath,lnk.absolutePath)
            }

            if(lnk.exists()) {
                status = 1
            }else {
                throw Exception("Process 'CreateDesktopShortcutProcess' failed")
            }
        }else {
            println("[CreateDesktopShortcutProcess] Skipping desktop shortcut")
            status = 1
        }


    }

}