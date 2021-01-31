package backend

import backend.processes.installation.CreateDesktopShortcutProcess
import backend.processes.installation.CreateStartMenuShortcutProcess
import backend.processes.installation.DownloadProcess
import backend.processes.installation.UnzipProcess

object InstallationManager {

    /**
     * List of all processes which have to be executed during installation
     */
    private val processes = listOf(DownloadProcess(), UnzipProcess(), CreateDesktopShortcutProcess(), CreateStartMenuShortcutProcess())

    fun startInstallation() {
        for(process in processes) {
            process.run()
            Thread.sleep(1000)
        }

        println("[System] Installation was successful!")
    }

}