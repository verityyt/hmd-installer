package frontend.widgets

import frontend.Window
import frontend.utils.CustomFont
import frontend.utils.Screen
import frontend.utils.Widget
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.geom.RoundRectangle2D
import java.awt.image.ImageObserver
import java.io.File
import java.net.URI
import javax.imageio.ImageIO
import javax.swing.JFileChooser
import javax.swing.UIManager

class DirectoryChooserWidget(
    override var x: Int,
    private val y: Int,
    private val w: Int,
    private val h: Int,
    private val arc: Int,
    private val stroke: Int,
    val parent: Screen
) : Widget() {

    var text = ""

    var overrideColor: Color? = null

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g2.paint = if (overrideColor == null) {
                Color.black
        } else {
            overrideColor
        }
        g2.stroke = BasicStroke(stroke.toFloat())
        g2.draw(
            RoundRectangle2D.Float(
                parent.originX + x.toFloat(),
                y.toFloat(),
                w.toFloat(),
                h.toFloat(),
                arc.toFloat(),
                arc.toFloat()
            )
        )

        g.font = CustomFont.light?.deriveFont(16f)
        g.color = Color.black
        g.drawString(text, parent.originX + x + 5, y + 21)

    }

    override fun click(x: Int, y: Int) {
        if (x > (parent.originX + this.x) && x < (parent.originX + this.x + this.w) && y > this.y && y < (this.y + this.h + 10)) {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

            val chooser = JFileChooser("C:\\")
            chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY

            val choice = chooser.showDialog(Window.window, "Select")
            if (choice == JFileChooser.APPROVE_OPTION) {
                text = chooser.selectedFile.absolutePath + "\\Hardware Monitoring Display\\"
            }

        }
    }

    override fun hover(x: Int, y: Int) {}

    override fun type(e: KeyEvent) {}

    private fun isAllowed(keyCode: Int, keyChar: Char): Boolean {
        return (keyCode in 48..57) || (keyCode in 65..90) || (keyCode in 96..105) || (keyCode == 32) || (keyCode == 47) || (keyCode == 92) || (keyCode == 46) || (keyChar == '\\')
    }

    fun error() {
        Thread {
            overrideColor = Color.decode("#e74c3c")

            for (i in 1..5) {
                wiggle()
                Thread.sleep(50)
            }

            overrideColor = null
        }.start()
    }

    private fun wiggle() {
        Thread {
            x += 10
            Thread.sleep(10)
            x -= 20
            Thread.sleep(10)
            x += 10
        }.start()
    }

}