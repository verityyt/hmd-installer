package frontend

import HMDInstaller
import frontend.listener.KeyListener
import frontend.listener.MouseListener
import frontend.listener.MouseMotionListener
import frontend.screen.LandingScreen
import frontend.screen.PropertiesScreen
import frontend.utils.CustomFont
import frontend.utils.Screen
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.WindowConstants

object Window {

    lateinit var window: JFrame
    lateinit var component: JComponent

    var screen: Screen = LandingScreen()
    var nextScreen: Screen = PropertiesScreen()

    fun build() {
        Thread {

            CustomFont.registerFonts()

            component = object : JComponent() {

                override fun paint(g: Graphics?) {
                    val g2 = g as Graphics2D

                    g.setRenderingHint(
                        RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY
                    )
                    g.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                    )
                    g.setRenderingHint(
                        RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                    )
                    g.setRenderingHint(
                        RenderingHints.KEY_STROKE_CONTROL,
                        RenderingHints.VALUE_STROKE_NORMALIZE
                    )
                    g.setRenderingHint(
                        RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR
                    )
                    g.setRenderingHint(
                        RenderingHints.KEY_FRACTIONALMETRICS,
                        RenderingHints.VALUE_FRACTIONALMETRICS_ON
                    )

                    g.color = Color.white
                    g.drawRect(0,0,800,775)

                    screen.paint(g, g2, this)
                    nextScreen.paint(g, g2, this)

                }

            }

            window = JFrame()
            window.add(component)

            window.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

            window.addMouseListener(MouseListener())
            window.addMouseMotionListener(MouseMotionListener())
            window.addKeyListener(KeyListener())

            window.isUndecorated = false
            window.setSize(800, 775)
            window.isResizable = false
            window.isAlwaysOnTop = false
            window.title = "Hardware Monitoring Display | v${HMDInstaller.version} Installer"
            window.iconImage = ImageIO.read(File("files/images/WindowIcon.png"))

            window.isVisible = true

            while (true) {
                Thread.sleep(1000 /  60)
                window.repaint()
            }
        }.start()
    }

}