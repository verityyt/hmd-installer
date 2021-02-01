package backend

import backend.processes.installation.*

object InstallationManager {

    /**
     * List of all processes which have to be executed during installation
     */
    private val processes = listOf(DownloadProcess(), UnzipProcess(), CreateDesktopShortcutProcess(), CreateStartMenuShortcutProcess(), CreateConfigurationProcess())

    fun startInstallation() {
        for(process in processes) {
            process.run()
            Thread.sleep(1000)
        }

        println("[System] Installation was successful!")
    }

}