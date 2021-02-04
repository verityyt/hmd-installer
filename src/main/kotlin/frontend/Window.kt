package frontend

import frontend.listener.KeyListener
import frontend.listener.MouseListener
import frontend.listener.MouseMotionListener
import frontend.screen.FinishedScreen
import frontend.screen.LandingScreen
import frontend.screen.PropertiesScreen
import frontend.utils.CustomFont
import frontend.utils.Screen
import java.awt.*
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.WindowConstants

object Window {

    lateinit var window: JFrame
    lateinit var component: JComponent

    var screen: Screen = LandingScreen()
    var nextScreen: Screen? = PropertiesScreen()

    lateinit var graphic: Graphics
    lateinit var graphic2d: Graphics2D

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

                    g.setRenderingHint(
                        RenderingHints.KEY_STROKE_CONTROL,
                        RenderingHints.VALUE_STROKE_PURE
                    )

                    screen.paint(g, g2, this)

                    graphic = g
                    graphic2d = g2

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
            window.title = "Hardware Monitoring Display | Installer"
            window.iconImage = ImageIO.read(File("files/images/WindowIcon.png"))

            window.isVisible = true

            while (true) {
                Thread.sleep(1000 / 60)
                window.repaint()
            }
        }.start()
    }

    fun drawError(errorCode: Int, errorText: String? = null) {
        val g = graphic

        g.color = Color.WHITE
        g.fillRect(0, 0, 800, 775)

        g.color = Color.BLACK
        g.font = CustomFont.regular?.deriveFont(36f)
        g.drawString("An error occurred", 260, 60)
        g.fillRect(200, 75, 400, 3)

        g.font = CustomFont.regular?.deriveFont(24f)
        g.drawString("Error Code $errorCode", 315, 190)

        g.font = CustomFont.light?.deriveFont(24f)

        when (errorCode) {
            403 -> {
                g.drawString("Running the installer as administrator could fix this error.", 100, 225)
                g.drawString("1.  Right click the .exe file", 100, 295)
                g.drawString("2. Click \"Run as administrator\"", 100, 330)
                g.drawString("3. Continue with the installation", 100, 365)
            }
            408 -> {
                g.drawString("Please connect your pc with the internet.", 190, 225)
            }
            400 -> {
                g.drawString("Please close the installer, and try again.", 195, 225)
                CustomFont.drawCentredString(
                    g,
                    Rectangle(40, 225, 720, 50),
                    "(${errorText!!})",
                    Color.BLACK,
                    CustomFont.light!!.deriveFont(24f)
                )
            }
            else -> {
                g.drawString("Unknown error code", 283, 225)
            }
        }

    }

}