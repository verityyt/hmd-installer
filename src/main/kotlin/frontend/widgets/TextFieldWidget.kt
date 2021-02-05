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

class TextFieldWidget(
    override var x: Int,
    private val y: Int,
    private val w: Int,
    private val h: Int,
    private val arc: Int,
    private val stroke: Int,
    val parent: Screen,
    private val standard: String = "",
    private var questionLink: String? = null
) : Widget() {

    private var focused = false
    var text = ""

    init {
        text = standard
    }

    var overrideColor: Color? = null

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g2.paint = if (overrideColor == null) {
            if (focused) {
                Color.decode("#BFBFBF")
            } else {
                Color.black
            }
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

        if(questionLink != null) {
            g.drawImage(ImageIO.read(File("files\\images\\questionmark.png")), parent.originX + x + w - 20, y + 7, observer)
        }

    }

    override fun click(x: Int, y: Int) {
        focused =
            (x > (parent.originX + this.x) && x < (parent.originX + this.x + this.w) && y > this.y && y < (this.y + this.h + 10))

        if(questionLink != null) {
            if(x > (parent.originX + this.x + this.w - 50) && x < (parent.originX + this.x + this.w) && y > this.y && y < (this.y + this.h + 10)) {
                Desktop.getDesktop().browse(URI(questionLink!!))
            }
        }
    }

    override fun hover(x: Int, y: Int) {}

    override fun type(e: KeyEvent) {
        if (focused) {
            when (e.keyCode) {
                8 -> {
                    if (text.length != 0) {
                        text = text.substring(0, text.length - 1)
                    }
                }
                10 -> focused = false
                27 -> focused = false
                else -> {
                    if (isAllowed(e.keyCode, e.keyChar)) {
                        text += e.keyChar.toString()
                    }
                }
            }
        }
    }

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