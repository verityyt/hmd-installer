package frontend.widgets

import frontend.utils.Widget
import frontend.utils.CustomFont
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.geom.RoundRectangle2D
import java.awt.image.ImageObserver

class ButtonWidget(
    var x: Int,
    private val y: Int,
    private val w: Int,
    private val h: Int,
    private val arc: Int,
    private val text: String,
    private val stroke: Int,
    private val fontSize: Int,
    private val available: Boolean,
    private val action: () -> Unit
) : Widget() {

    var hovered = false

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {
        if (!available) {
            g2.paint = Color.decode("#BFBFBF")
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

            CustomFont.drawCentredString(
                g,
                Rectangle(x, y, w, h),
                text,
                Color.decode("#BFBFBF"),
                CustomFont.regular!!.deriveFont(fontSize.toFloat())
            )
        } else {
            if (hovered) {
                g2.paint = Color.black
                g2.stroke = BasicStroke(stroke.toFloat())
                g2.fill(
                    RoundRectangle2D.Float(
                        x.toFloat(),
                        y.toFloat(),
                        w.toFloat(),
                        h.toFloat(),
                        arc.toFloat(),
                        arc.toFloat()
                    )
                )

                CustomFont.drawCentredString(
                    g,
                    Rectangle(x, y, w, h),
                    text,
                    Color.white,
                    CustomFont.regular!!.deriveFont(fontSize.toFloat())
                )
            } else {
                g2.paint = Color.black
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

                CustomFont.drawCentredString(
                    g,
                    Rectangle(x, y, w, h),
                    text,
                    Color.black,
                    CustomFont.regular!!.deriveFont(fontSize.toFloat())
                )
            }
        }
    }

    override fun click(x: Int, y: Int) {
        if (x > this.x && x < (this.x + this.w) && y > this.y && this.y < (this.y + h) && available) {
            action()
        }
    }

    override fun hover(x: Int, y: Int) {
        hovered = (x > this.x && x < (this.x + this.w) && y > this.y && this.y < (this.y + h))
    }

    override fun type(e: KeyEvent) { }

}