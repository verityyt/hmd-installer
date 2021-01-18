package frontend.screen

import frontend.utils.Screen
import frontend.Window
import frontend.utils.CustomFont
import frontend.widgets.ButtonWidget
import frontend.utils.Widget
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO
import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI

class LandingScreen : Screen() {

    override var originX: Int = 0

    private var installButton = ButtonWidget(originX + 230, 660, 350, 60, 25, "Install", 3, 30, true) {
        /*Window.screen = PropertiesScreen()*/
        animate(1, 5)
    }

    private var uninstallButton = ButtonWidget(originX + 230, 590, 350, 60, 25, "Uninstall", 3, 30, false) { }

    override val widgets: List<Widget> = listOf(installButton, uninstallButton)

    override fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver) {

        g.drawImage(ImageIO.read(File("files/images/Rocket.png")), originX + 300, 30, 200, 205, observer)

        g.color = Color.black
        g.font = CustomFont.medium?.deriveFont(24f)
        g.drawString("Welcome to Hardware Monitoring Display,", originX + 170, 315)

        g.font = CustomFont.light?.deriveFont(24f)
        g.drawString("the next generation hardware monitoring software, with useful", originX + 80, 350)
        g.drawString("features and customizable userinterface. You’re able to change", originX + 80, 380)
        g.drawString("every single color of the userinterface to give it your own style.", originX + 80, 410)
        g.drawString("Furthermore you can set alarms so that if you lose sight of your", originX + 80, 440)
        g.drawString("temperatures, you will still notice if any part of your computer", originX + 80, 470)
        g.drawString("reaches a critical temperature.", originX + 80, 500)

        installButton.paint(g, g2, observer)
        uninstallButton.paint(g, g2, observer)

    }

    private fun animate(speed: Long, tick: Int) {

        Thread {
            while(true) {
                if(Window.nextScreen.originX != (-799 + tick)) {
                    Thread.sleep(speed)
                    originX-=tick
                    installButton.x-=tick
                    uninstallButton.x-=tick
                }else {
                    Thread.currentThread().interrupt()
                }
            }
        }.start()

        Thread {
            while(true) {
                if(Window.nextScreen is PropertiesScreen) {
                    if(Window.nextScreen.originX != (0 + tick)) {
                        Thread.sleep(speed)

                        val next = Window.nextScreen as PropertiesScreen

                        next.originX-=tick
                        next.desktopLnk.x-=tick
                        next.startLnk.x-=tick
                        next.dirText.x-=tick
                        next.lightLnk.x-=tick
                        next.darkLnk.x-=tick
                        next.dri1FilText.x-=tick
                        next.dri2FilText.x-=tick
                        next.dri1NaText.x-=tick
                        next.dri2NaText.x-=tick
                    }else {
                        Thread.currentThread().interrupt()
                    }
                }
            }
        }.start()

    }

}