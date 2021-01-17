package frontend.screen

import frontend.Screen
import frontend.utils.CustomFont
import frontend.widgets.ButtonWidget
import frontend.widgets.Widget
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class LandingScreen : Screen() {

    private val installButton = ButtonWidget(230, 660, 350, 60, 25, "Install", 3, 30, true) { }

    private val uninstallButton = ButtonWidget(230, 590, 350, 60, 25, "Uninstall", 3, 30, false) { }

    override val widgets: List<Widget> = listOf(installButton, uninstallButton)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.drawImage(ImageIO.read(File("files/images/Rocket.png")), 300, 30, 200, 205, observer)

        g.color = Color.black
        g.font = CustomFont.medium?.deriveFont(24f)
        g.drawString("Welcome to Hardware Monitoring Display,", 170, 315)

        g.font = CustomFont.light?.deriveFont(24f)
        g.drawString("the next generation hardware monitoring software, with useful", 80, 350)
        g.drawString("features and customizable userinterface. You’re able to change", 80, 380)
        g.drawString("every single color of the userinterface to give it your own style.", 80, 410)
        g.drawString("Furthermore you can set alarms so that if you lose sight of your", 80, 440)
        g.drawString("temperatures, you will still notice if any part of your computer", 80, 470)
        g.drawString("reaches a critical temperature.", 80, 500)

        installButton.paint(g, g2, observer)
        uninstallButton.paint(g, g2, observer)

    }

}