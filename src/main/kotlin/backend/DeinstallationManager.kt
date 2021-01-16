package backend

import backend.processes.deinstallation.DeleteDesktopShortcutProcess
import backend.processes.deinstallation.DeleteFilesProcess
import backend.processes.deinstallation.DeleteStartMenuShortcutProcess

object DeinstallationManager {

    private val processes = listOf(DeleteStartMenuShortcutProcess(), DeleteDesktopShortcutProcess(), DeleteFilesProcess())

    fun startDeinstallation() {
        for(process in processes) {
            process.run()
        }

        println("[System] Deinstallation was successful!")
    }

}