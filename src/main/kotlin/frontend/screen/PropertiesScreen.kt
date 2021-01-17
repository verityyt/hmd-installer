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

    private val desktopLnk = CheckboxWidget(370, 122, 30, 10, 3, true)
    private val startLnk = CheckboxWidget(370, 172, 30, 10, 3, true)
    private val dirText =
        TextFieldWidget(370, 225, 400, 30, 10, 3, "C:\\Program Files (x86)\\Hardware Monitoring Display")

    private val lightLnk = CheckboxWidget(233, 377, 30, 10, 3, true)
    private val darkLnk = CheckboxWidget(357, 377, 30, 10, 3)
    private val dri1FilText = TextFieldWidget(233, 433, 158, 30, 10, 3) // Drive 1 Filter
    private val dri2FilText = TextFieldWidget(233, 483, 158, 30, 10, 3) // Drive 2 Filter
    private val dri1NaText = TextFieldWidget(233, 533, 158, 30, 10, 3) // Drive 1 Name
    private val dri2NaText = TextFieldWidget(233, 583, 158, 30, 10, 3) // Drive 2 Name

    override val widgets: List<Widget> =
        listOf(desktopLnk, startLnk, lightLnk, darkLnk, dirText, dri1FilText, dri2FilText, dri1NaText, dri2NaText)

    var nextHovered = false

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        lightLnk.link = darkLnk
        darkLnk.link = lightLnk

        /* Properties */

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Installation properties", 210, 60)
        g.fillRect(180, 75, 400, 3)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Create desktop shortcut", 30, 145)
        desktopLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Create start menu entry", 30, 195)
        startLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Installation directory", 30, 245)
        dirText.paint(g, g2, observer)

        /* Configuration */

        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Configuration", 275, 320)
        g.fillRect(180, 335, 400, 3)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Theme", 30, 400)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Light", 170, 400)
        lightLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Dark", 300, 400)
        darkLnk.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #1 Filter", 30, 455)
        dri1FilText.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #2 Filter", 30, 505)
        dri2FilText.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #1 Name", 30, 555)
        dri1NaText.paint(g, g2, observer)

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Drive #2 Name", 30, 605)
        dri2NaText.paint(g, g2, observer)

        /* Next */

        if(nextHovered) {
            g.drawImage(ImageIO.read(File("files/images/arrow_hov.png")), 675, 675, 90, 50,  observer)
        }else {
            g.drawImage(ImageIO.read(File("files/images/arrow.png")), 675, 675, 90, 50,  observer)
        }

    }

}