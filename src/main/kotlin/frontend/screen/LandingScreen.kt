package frontend.screen

import frontend.Window
import frontend.screen.installation.InstallationProgressScreen
import frontend.screen.installation.InstallationPropertiesScreen
import frontend.screen.uninstallation.UninstallationProgressScreen
import frontend.screen.uninstallation.UninstallationPropertiesScreen
import frontend.transitions.EaseScreenSwitchTransition
import frontend.utils.CustomFont
import frontend.utils.Screen
import frontend.utils.Widget
import frontend.widgets.ButtonWidget
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class LandingScreen : Screen() {

    override var originX: Int = 0

    private var installButton = ButtonWidget(originX + 230, 660, 350, 60, 25, "Install", 3, 30, this, true) {
        Window.nextScreen = InstallationPropertiesScreen()
        EaseScreenSwitchTransition(InstallationProgressScreen()).start()
    }

    private var uninstallButton = ButtonWidget(originX + 230, 590, 350, 60, 25, "Uninstall", 3, 30, this, true) {
        Window.nextScreen = UninstallationPropertiesScreen()
        EaseScreenSwitchTransition(UninstallationProgressScreen()).start()
    }

    override val widgets: List<Widget> = listOf(installButton, uninstallButton)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.drawImage(ImageIO.read(File("files/images/Rocket.png")), originX + 300, 30, 200, 205, observer)

        g.color = Color.black
        g.font = CustomFont.medium?.deriveFont(24f)
        g.drawString("Welcome to Hardware Monitoring Display,", originX + 170, 315)

        g.font = CustomFont.light?.deriveFont(24f)
        g.drawString("the next generation hardware monitoring software, with useful", originX + 80, 350)
        g.drawString("features and customizable userinterface. You’re able to change", originX + 80, 380)
        g.drawString("every single color of the userinterface to give it your own style.", originX + 80, 410)
        g.drawString("Furthermore you can set alarms so that if you lose sight of your", originX + 80, 440)
        g.drawString("temperatures, you will still notice if any part of your computer", originX + 80, 470)
        g.drawString("reaches a critical temperature.", originX + 80, 500)

        installButton.paint(g, g2, observer)
        uninstallButton.paint(g, g2, observer)

    }

    override fun afterSwitch() {}

}