import frontend.Window
import frontend.utils.CustomFont
import java.awt.*
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.WindowConstants


object HMDInstaller {

    @JvmStatic
    fun main(args: Array<String>) {
        Window.build()
        /*InstallationManager.startInstallation()*/
    }

    fun showError(code: Int, text: String? = null) {

        Window.window.dispose()

        val frame = JFrame()
        val component = object : JComponent() {

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

                g.color = Color.black
                g.font = CustomFont.regular?.deriveFont(36f)
                g.drawString("An error occured", 260, 40)
                g.fillRect(200, 50, 400, 3)

                g.font = CustomFont.regular?.deriveFont(24f)
                g.drawString("Error Code $code", 320, 80)

                g.font = CustomFont.light!!.deriveFont(24f)

                when(code) {
                    403 -> {
                        g.drawString("Running the installer as administrator could fix this error", 110, 150)
                        g.drawString("1.  Right click the .exe file", 110, 200)
                        g.drawString("2. Click “Run as administrator”", 110, 225)
                        g.drawString("3. Continue with the installation", 110, 250)
                    }
                    408 -> {
                        g.drawString("Please connect your pc with the internet.", 190, 150)
                    }
                    400 -> {
                        g.drawString("Please close the installer, and try again.", 195, 180)
                        CustomFont.drawCentredString(
                            g,
                            Rectangle(20, 125, 760, 25),
                            "(${text})",
                            Color.BLACK,
                            CustomFont.light!!.deriveFont(24f)
                        )
                    }
                    else -> {
                        g.drawString("Unknown error code", 283, 150)
                    }
                }

            }

        }

        frame.add(component)

        frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        frame.isUndecorated = false
        frame.setSize(800, 350)
        frame.isResizable = false
        frame.isAlwaysOnTop = true
        frame.title = "Hardware Monitoring Display | Installer"
        frame.iconImage = ImageIO.read(File("files/images/WindowIcon.png"))

        val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
        val width = screenSize.getWidth()
        val height = screenSize.getHeight()
        frame.location = Point(((width / 2) - 400).toInt(), ((height / 2) - 175).toInt())

        frame.isVisible = true


    }

}