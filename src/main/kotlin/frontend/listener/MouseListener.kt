package frontend.listener

import backend.InstallationProperties
import frontend.Window
import frontend.screen.PropertiesScreen
import frontend.transitions.EaseScreenSwitchTransition
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseListener : MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
        if(e != null) {
            for(widget in Window.screen.widgets) {
                widget.click(e.x, e.y)
            }

            if(Window.screen is PropertiesScreen) {
                if(e.x > 675 && e.x < (675 + 90) && e.y > 675 && e.y < (675 + 50 + 20)) {
                    InstallationProperties.desktopLnk = (Window.screen as PropertiesScreen).desktopLnk.checked
                    InstallationProperties.startLnk = (Window.screen as PropertiesScreen).startLnk.checked
                    InstallationProperties.instDir = (Window.screen as PropertiesScreen).dirText.text
                    EaseScreenSwitchTransition(null).start()
                }
            }

        }
    }

    override fun mousePressed(e: MouseEvent?) { }

    override fun mouseReleased(e: MouseEvent?) { }

    override fun mouseEntered(e: MouseEvent?) { }

    override fun mouseExited(e: MouseEvent?) { }

}