package frontend.screen

import backend.Process
import backend.processes.installation.*
import frontend.transitions.EaseScreenSwitchTransition
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

    private val downloadProc = DownloadProcess()
    private val unzipProc = UnzipProcess()
    private val dskLnkProc = CreateDesktopShortcutProcess()
    private val startLnkProc = CreateStartMenuShortcutProcess()
    private val configProc = CreateConfigurationProcess()

    private val downloadSpinnerWidget = LoadingSpinnerWidget(35,125,50, 3f, this, downloadProc, unzipProc)
    private val unzipSpinnerWidget = LoadingSpinnerWidget(35,200,50, 3f, this, unzipProc, configProc)
    private val configSpinnerWidget = LoadingSpinnerWidget(35,275,50, 3f, this, configProc, dskLnkProc)
    private val desktopLnkSpinnerWidget = LoadingSpinnerWidget(35,350,50, 3f, this, dskLnkProc, startLnkProc)
    private val startMenuLnkSpinnerWidget = LoadingSpinnerWidget(35,425,50, 3f, this, dskLnkProc, object : Process() {
        override var test: Boolean = true

        override var status: Int = 0

        override fun run() {
            EaseScreenSwitchTransition(null).start()
            status = 1
        }

    })

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

    override fun afterSwitch() {
        downloadProc.run()
    }

}