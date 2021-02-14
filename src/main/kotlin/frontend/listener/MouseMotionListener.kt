package frontend.listener

import frontend.Window
import frontend.screen.installation.InstallationPropertiesScreen
import frontend.screen.uninstallation.UninstallationPropertiesScreen
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener

class MouseMotionListener : MouseMotionListener {

    override fun mouseDragged(e: MouseEvent?) { }

    override fun mouseMoved(e: MouseEvent?) {
        if(e != null) {
            for(widget in Window.screen.widgets) {
                widget.hover(e.x, e.y)
            }

            if(Window.screen is InstallationPropertiesScreen) {
                (Window.screen as InstallationPropertiesScreen).nextHovered = (e.x > 675 && e.x < (675 + 90) && e.y > 675 && e.y < (675 + 50 + 20))
            }else if(Window.screen is UninstallationPropertiesScreen) {
                (Window.screen as UninstallationPropertiesScreen).nextHovered = (e.x > 675 && e.x < (675 + 90) && e.y > 675 && e.y < (675 + 50 + 20))
            }

        }
    }

}