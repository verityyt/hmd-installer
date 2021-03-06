package frontend.screen.installation

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

class InstallationFinishedScreen : Screen() {

    override var originX: Int = 800

    private var launch = ButtonWidget(200, 620, 350, 60, 25, "Launch", 3, 30, this, true) {
        ProcessBuilder("${InstallationProperties.instDir}hmd.exe").start()
        exitProcess(1000)
    }

    override val widgets: List<Widget> = listOf(launch)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.drawImage(ImageIO.read(File("files/images/check.png")), originX + 300, 150, 200, 200, observer)

        g.font = CustomFont.medium?.deriveFont(30f)
        g.color = Color.decode("#2ECC71")
        g.drawString("Thanks for installing Hardware Monitoring Display", originX + 55, 450)

        launch.paint(g, g2, observer)

    }

    override fun afterSwitch() {}

}