import backend.UninstallationManager

object HMDInstaller {

    @JvmStatic
    fun main(args: Array<String>) {
        UninstallationManager.startUninstallation()
    }

}