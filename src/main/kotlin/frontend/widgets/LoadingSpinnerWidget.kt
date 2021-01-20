package frontend.widgets

import frontend.Window
import frontend.utils.Screen
import frontend.utils.Widget
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.geom.Ellipse2D
import java.awt.image.ImageObserver
import java.io.File
import java.util.*
import javax.imageio.ImageIO

class LoadingSpinnerWidget(
    override var x: Int,
    private val y: Int,
    private val s: Int,
    private val stroke: Float,
    private val parent: Screen
) : Widget() {

    private var rotation = 0
    private var rectW = s
    private var circleColor = Color.black
    private var circleAlpha = 0.0f

    init {
        Thread {
            while (true) {
                if (rotation == 360) {
                    rotation = 0
                }

                rotation += 1
                Thread.sleep(2)
            }
        }.start()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                morphCircle()
            }
        }, 10000)

    }

    private fun morphCircle() {
        Thread {
            while (true) {

                rectW -= 1
                circleColor = Color.decode("#2ECC71")
                if(circleAlpha != 1.0f) {
                    circleAlpha += 0.5f
                }

                if (rectW <= 0) {
                    Thread.currentThread().interrupt()
                } else {
                    Thread.sleep(15)
                }
            }
        }.start()
    }

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g2.stroke = BasicStroke(stroke)
        g2.color = circleColor
        g2.draw(Ellipse2D.Float(parent.originX.toFloat() + x.toFloat(), y.toFloat(), s.toFloat(), s.toFloat()))

        val oldTransformer = g2.transform
        g2.rotate(Math.toRadians(rotation.toDouble()), parent.originX + x + (s / 2).toDouble(), y + (s / 2).toDouble())
        g2.color = Window.window.background
        g2.fillRect(parent.originX + x - 12, y + 20, (rectW * 1.5).toInt(), rectW / 5)
        g2.transform = oldTransformer

        val oldComp = g2.composite
        val comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, circleAlpha)
        g2.composite = comp
        g.drawImage(ImageIO.read(File("files/images/check.png")), parent.originX + x + (s * 0.2).toInt(), y + (s * 0.2).toInt(), (s * 0.6).toInt(), (s * 0.6).toInt(), observer)
        g2.composite = oldComp

    }

    override fun click(x: Int, y: Int) {}

    override fun hover(x: Int, y: Int) {}

    override fun type(e: KeyEvent) {}

}