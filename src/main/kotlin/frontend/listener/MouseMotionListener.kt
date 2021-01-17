package frontend.listener

import frontend.Window
import frontend.screen.PropertiesScreen
import java.awt.Color
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener

class MouseMotionListener : MouseMotionListener {

    override fun mouseDragged(e: MouseEvent?) { }

    override fun mouseMoved(e: MouseEvent?) {
        if(e != null) {
            for(widget in Window.screen.widgets) {
                widget.hover(e.x, e.y)
            }

            if(Window.screen is PropertiesScreen) {
                (Window.screen as PropertiesScreen).nextHovered = (e.x > 675 && e.x < (675 + 90) && e.y > 675 && e.y < (675 + 50 + 20))
            }

        }
    }

}