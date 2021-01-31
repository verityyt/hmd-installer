package frontend.utils

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver

abstract class Screen {

    /**
     * Original X-location of the screen
     * All content is drawn at **originX + ?**
     */
    abstract var originX: Int

    /**
     * All widgets of the current screen
     */
    abstract val widgets: List<Widget>

    abstract fun paint(g: Graphics, g2: Graphics2D, observer: ImageObserver)

    /**
     * Function is called when switch-transition (to this screen) finished/at end
     */
    abstract fun afterSwitch()

}