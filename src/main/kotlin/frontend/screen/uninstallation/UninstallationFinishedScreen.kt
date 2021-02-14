package frontend.screen.uninstallation

import backend.InstallationProperties
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
import kotlin.system.exitProcess

class UninstallationFinishedScreen : Screen() {

    override var originX: Int = 800

    override val widgets: List<Widget> = listOf()

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.drawImage(ImageIO.read(File("files/images/check.png")), originX + 300, 150, 200, 200, observer)

        g.font = CustomFont.medium?.deriveFont(30f)
        g.color = Color.decode("#2ECC71")
        g.drawString("Hardware Monitoring Display successfully uninstalled!", originX + 35, 450)

    }

    override fun afterSwitch() {}

}