package frontend.screen.uninstallation

import backend.Process
import backend.processes.installation.*
import backend.processes.uninstallation.DeleteDesktopShortcutProcess
import backend.processes.uninstallation.DeleteFilesProcess
import backend.processes.uninstallation.DeleteStartMenuShortcutProcess
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

class UninstallationProgressScreen : Screen() {

    private val delFiles = DeleteFilesProcess()
    private val delDskLnk = DeleteDesktopShortcutProcess()
    private val delStartLnk = DeleteStartMenuShortcutProcess()

    private val delFilesSpinnerWidget = LoadingSpinnerWidget(35,125,50, 3f, this, delFiles, delDskLnk)
    private val delDskSpinnerWidget = LoadingSpinnerWidget(35,200,50, 3f, this, delDskLnk, delStartLnk)
    private val startMenuLnkSpinnerWidget = LoadingSpinnerWidget(35,275,50, 3f, this, delDskLnk, object : Process() {
        override var test: Boolean = true

        override var status: Int = 0

        override fun run() {
            EaseScreenSwitchTransition(null).start()
            status = 1
        }

    })

    override var originX: Int = 800

    override val widgets: List<Widget> = listOf(delFilesSpinnerWidget, delDskSpinnerWidget, startMenuLnkSpinnerWidget)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.color = Color.black
        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("Installation in progress", originX + 225, 60)
        g.fillRect(originX + 200, 75, 400, 3)

        delFilesSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Delete files", originX + 105, 165)

        delDskSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Delete desktop shortcut", originX + 105, 240)

        startMenuLnkSpinnerWidget.paint(g,g2,observer)
        g.color = Color.black
        g.drawString("Delete start menu entry", originX + 105, 315)

    }

    override fun afterSwitch() {
        delFiles.run()
    }

}