package backend

import backend.processes.uninstallation.DeleteDesktopShortcutProcess
import backend.processes.uninstallation.DeleteFilesProcess
import backend.processes.uninstallation.DeleteStartMenuShortcutProcess

object UninstallationManager {

    /**
     * List of all processes which have to be executed during uninstallation
     */
    private val processes = listOf(DeleteStartMenuShortcutProcess(), DeleteDesktopShortcutProcess(), DeleteFilesProcess())

    fun startUninstallation() {
        for(process in processes) {
            process.run()
        }

        println("[System] Uninstallation was successful!")
    }

}