package frontend.utils

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver

abstract class Screen {

    abstract var originX: Int

    abstract val widgets: List<Widget>

    abstract fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver)

    abstract fun afterSwitch()

}