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

    private val downloadSpinnerWidget = LoadingSpinnerWidget(35,125,50, 3f, this)
    private val unzipSpinnerWidget = LoadingSpinnerWidget(35,200,50, 3f, this)
    private val configSpinnerWidget = LoadingSpinnerWidget(35,275,50, 3f, this)
    private val desktopLnkSpinnerWidget = LoadingSpinnerWidget(35,350,50, 3f, this)
    private val startMenuLnkSpinnerWidget = LoadingSpinnerWidget(35,425,50, 3f, this)

    override var originX: Int = 800

    override val widgets: List<Widget> = listOf(downloadSpinnerWidget, unzipSpinnerWidget, configSpinnerWidget, desktopLnkSpinnerWidget, startMenuLnkSpinnerWidget)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Installation in progress", originX + 210, 60)
        g.fillRect(originX + 180, 75, 400, 3)

        downloadSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Download latest version", originX + 105, 165)

        unzipSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Unzip file", originX + 105, 240)

        configSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Create configuration", originX + 105, 315)

        desktopLnkSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Create desktop shortcut", originX + 105, 390)

        startMenuLnkSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Create start menu entry", originX + 105, 465)

    }

}