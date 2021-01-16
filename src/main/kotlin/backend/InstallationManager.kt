package backend

import backend.processes.installation.CreateDesktopShortcutProcess
import backend.processes.installation.CreateStartMenuShortcutProcess
import backend.processes.installation.UnzipProcess

object InstallationManager {

    private val processes = listOf(UnzipProcess(), CreateDesktopShortcutProcess(), CreateStartMenuShortcutProcess())

    fun startInstallation() {
        for(process in processes) {
            process.run()
        }

        println("[System] Installation was successful!")
    }

}