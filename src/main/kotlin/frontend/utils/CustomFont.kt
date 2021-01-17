package frontend.utils

import java.awt.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


object CustomFont {

    var regular: Font? = null
    var light: Font? = null
    var medium: Font? = null

    fun registerFonts() {
        registerRegular()
        registerLight()
        registerMedium()

        println("Registered Fonts!")
    }


    fun registerRegular() {
        regular =
            Font.createFont(Font.TRUETYPE_FONT, File("files/fonts/Product-Sans-Regular.ttf"))
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        ge.registerFont(regular)
    }

    fun registerLight() {
        light =
            Font.createFont(Font.TRUETYPE_FONT, File("files/fonts/Product-Sans-Light.ttf"))
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        ge.registerFont(light)
    }

    fun registerMedium() {
        medium =
            Font.createFont(Font.TRUETYPE_FONT, File("files/fonts/Product-Sans-Medium.ttf"))
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        ge.registerFont(medium)
    }

    fun drawCentredString(graphics: Graphics, rect: Rectangle, text: String, color: Color, font: Font) {
        val metrics: FontMetrics = graphics.getFontMetrics(font)
        val x: Int = rect.x + (rect.width - metrics.stringWidth(text)) / 2
        val y: Int = rect.y + (rect.height - metrics.height) / 2 + metrics.ascent
        graphics.font = font
        graphics.color = color
        graphics.drawString(text, x, y)
    }

}