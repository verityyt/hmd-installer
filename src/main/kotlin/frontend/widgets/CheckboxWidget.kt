package frontend.widgets

import frontend.utils.CustomFont
import frontend.utils.Screen
import frontend.utils.Widget
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.geom.RoundRectangle2D
import java.awt.image.ImageObserver

class CheckboxWidget(override var x: Int, private val y: Int, private val l: Int, private val arc: Int, private val stroke: Int, val parent: Screen , private val standard: Boolean = false) : Widget() {

    var checked = false
    var link: CheckboxWidget? = null

    init {
        checked = standard
    }

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g2.paint = Color.black
        g2.stroke = BasicStroke(stroke.toFloat())
        g2.draw(RoundRectangle2D.Float(
            parent.originX + x.toFloat(),
            y.toFloat(),
            l.toFloat(),
            l.toFloat(),
            arc.toFloat(),
            arc.toFloat()
        ))

        if(checked) {
            g.font = CustomFont.medium?.deriveFont(24f)
            g.color = Color.black
            g.drawString("X", parent.originX + x + 8, y + 25)
        }

    }

    override fun click(x: Int, y: Int) {
        if(x > (parent.originX + this.x) && x < (parent.originX + this.x + this.l) && y > this.y && y < (this.y + this.l + 10)) {
            checked = !checked
            if(link != null) {
                link!!.checked = !checked
            }
        }
    }

    override fun hover(x: Int, y: Int) { }

    override fun type(e: KeyEvent) { }

}