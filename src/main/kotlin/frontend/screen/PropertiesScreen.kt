package frontend.screen

import frontend.utils.CustomFont
import frontend.utils.Screen
import frontend.utils.Widget
import frontend.widgets.CheckboxWidget
import frontend.widgets.TextFieldWidget
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

class PropertiesScreen : Screen() {

    override var originX: Int = 800

    val desktopLnk = CheckboxWidget(370, 122, 30, 10, 3,this, true)
    val startLnk = CheckboxWidget(370, 172, 30, 10, 3, this, true)
    val dirText =
        TextFieldWidget(370, 225, 400, 30, 10, 3, this, "C:\\Program Files (x86)\\Hardware Monitoring Display")

    val lightLnk = CheckboxWidget(233, 377, 30, 10, 3,this, true)
    val darkLnk = CheckboxWidget(357, 377, 30, 10, 3, this)
    val dri1FilText = TextFieldWidget(233, 433, 158, 30, 10, 3, this) // Drive 1 Filter
    val dri2FilText = TextFieldWidget(233, 483, 158, 30, 10, 3, this) // Drive 2 Filter
    val dri1NaText = TextFieldWidget(233, 533, 158, 30, 10, 3, this) // Drive 1 Name
    val dri2NaText = TextFieldWidget(233, 583, 158, 30, 10, 3, this) // Drive 2 Name

    override val widgets: List<Widget> =
        listOf(desktopLnk, startLnk, dirText, lightLnk, darkLnk, dri1FilText, dri2FilText, dri1NaText, dri2NaText)

    var nextHovered = false

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        lightLnk.link = darkLnk
        darkLnk.link = lightLnk

        /* Properties */

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Installation properties", originX + 210, 60)
        g.fillRect(originX + 180, 75, 400, 3)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Create desktop shortcut", originX + 30, 145)
        desktopLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Create start menu entry", originX + 30, 195)
        startLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Installation directory", originX + 30, 245)
        dirText.paint(g, g2, observer)

        /* Configuration */

        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Configuration", originX + 275, 320)
        g.fillRect(originX + 180, 335, 400, 3)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Theme", originX + 30, 400)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Light", originX + 170, 400)
        lightLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Dark", originX + 300, 400)
        darkLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #1 Filter", originX + 30, 455)
        dri1FilText.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #2 Filter", originX + 30, 505)
        dri2FilText.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #1 Name", originX + 30, 555)
        dri1NaText.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #2 Name", originX + 30, 605)
        dri2NaText.paint(g, g2, observer)

        /* Next */

        if(nextHovered) {
            g.drawImage(ImageIO.read(File("files/images/arrow_hov.png")), originX + 675, 675, 90, 50,  observer)
        }else {
            g.drawImage(ImageIO.read(File("files/images/arrow.png")), originX + 675, 675, 90, 50,  observer)
        }

    }

}