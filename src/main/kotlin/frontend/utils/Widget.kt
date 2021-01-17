package frontend.utils

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.image.ImageObserver

abstract class Widget {

    abstract fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver)

    abstract fun click(x: Int, y: Int)

    abstract fun hover(x: Int, y: Int)

    abstract fun type(e: KeyEvent)

}