package frontend.listener

import frontend.Window
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener

class MouseMotionListener : MouseMotionListener {

    override fun mouseDragged(e: MouseEvent?) { }

    override fun mouseMoved(e: MouseEvent?) {
        if(e != null) {
            for(widget in Window.screen.widgets) {
                widget.hover(e.x, e.y)
            }
        }
    }

}