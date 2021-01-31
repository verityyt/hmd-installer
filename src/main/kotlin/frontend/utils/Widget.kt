package frontend.utils

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.image.ImageObserver

abstract class Widget {

    /**
     * X-location of the widget
     */
    abstract var x: Int

    abstract fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver)

    /**
     * Method which is called when user clicked (anywhere) on the current screen
     */
    abstract fun click(x: Int, y: Int)

    /**
     * Method which is called when user moves his courser (anywhere) on the current screen
     */
    abstract fun hover(x: Int, y: Int)

    /**
     * Method which is called when user types (anywhere) on the current screen
     */
    abstract fun type(e: KeyEvent)

}