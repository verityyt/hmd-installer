package frontend.screen

import frontend.utils.CustomFont
import frontend.utils.Screen
import frontend.utils.Widget
import frontend.widgets.LoadingSpinnerWidget
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver

class ProgressScreen : Screen() {

    private val downloadSpinnerWidget = LoadingSpinnerWidget(35,275,50, 3f, this)

    override var originX: Int = 800

    override val widgets: List<Widget> = listOf(downloadSpinnerWidget)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Installation in progress", originX + 210, 60)
        g.fillRect(originX + 180, 75, 400, 3)

        downloadSpinnerWidget.paint(g,g2,observer)

    }

}