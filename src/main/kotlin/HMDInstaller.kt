import backend.DeinstallationManager

object HMDInstaller {

    @JvmStatic
    fun main(args: Array<String>) {
        DeinstallationManager.startDeinstallation()
    }

}