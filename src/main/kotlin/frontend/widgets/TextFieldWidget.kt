package frontend.widgets

import frontend.utils.CustomFont
import frontend.utils.Widget
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.geom.RoundRectangle2D
import java.awt.image.ImageObserver

class TextFieldWidget(
    private val x: Int,
    private val y: Int,
    private val w: Int,
    private val h: Int,
    private val arc: Int,
    private val stroke: Int,
    private val standard: String = ""
) : Widget() {

    private var focused = false
    var text = ""

    init {
        text = standard
    }

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g2.paint = if(focused) {
            Color.decode("#BFBFBF")
        }else {
            Color.black
        }
        g2.stroke = BasicStroke(stroke.toFloat())
        g2.draw(
            RoundRectangle2D.Float(
                x.toFloat(),
                y.toFloat(),
                w.toFloat(),
                h.toFloat(),
                arc.toFloat(),
                arc.toFloat()
            )
        )

        g.font = CustomFont.light?.deriveFont(16f)
        g.color = Color.black
        g.drawString(text, x + 5, y + 21)

    }

    override fun click(x: Int, y: Int) {
        focused = (x > this.x && x < (this.x + this.w) && y > this.y && y < (this.y + this.h + 10))
    }

    override fun hover(x: Int, y: Int) {}

    override fun type(e: KeyEvent) {
        if (focused) {
            when (e.keyCode) {
                8 -> {
                    if(text.length != 0) {
                        text = text.substring(0, text.length - 1)
                    }
                }
                10 -> focused = false
                27 -> focused = false
                else -> {
                    if(isAllowed(e.keyCode, e.keyChar)) {
                        text += e.keyChar.toString()
                    }
                }
            }
        }
    }

    private fun isAllowed(keyCode: Int, keyChar: Char): Boolean {
        return (keyCode in 48..57) || (keyCode in 65..90) || (keyCode in 96..105) || (keyCode == 32) || (keyCode == 47) || (keyCode == 92) || (keyCode == 46) || (keyChar == '\\')
    }

}