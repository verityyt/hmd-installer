package frontend.screen.uninstallation

import backend.InstallationProperties
import frontend.utils.CustomFont
import frontend.utils.Screen
import frontend.utils.Widget
import frontend.widgets.CheckboxWidget
import frontend.widgets.DirectoryChooserWidget
import frontend.widgets.TextFieldWidget
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class UninstallationPropertiesScreen : Screen() {

    override var originX: Int = 800

    var nextHovered = false
    val dirText = DirectoryChooserWidget(370, 200, 400, 30, 10, 3, this)

    override val widgets: List<Widget> = listOf(dirText)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        /* Properties */

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Uninstallation properties", originX + 230, 60)
        g.fillRect(originX + 200, 75, 400, 3)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Installation directory", originX + 30, 220)
        dirText.paint(g, g2, observer)

        /* Next */

        if (nextHovered) {
            g.drawImage(ImageIO.read(File("files/images/arrow_hov.png")), originX + 675, 675, 90, 50, observer)
        } else {
            g.drawImage(ImageIO.read(File("files/images/arrow.png")), originX + 675, 675, 90, 50, observer)
        }

    }

    override fun afterSwitch() {}

}