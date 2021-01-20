package frontend.screen

import frontend.utils.Screen
import frontend.utils.Widget
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver

class ProgressScreen : Screen() {

    override var originX: Int = 800

    override val widgets: List<Widget> = listOf()

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g2.stroke = BasicStroke(1f)
        g.color = Color.black
        g.drawRect(originX + 100,100,100,100)

    }

}