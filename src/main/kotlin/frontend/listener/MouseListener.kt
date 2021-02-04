package frontend.listener

import backend.InstallationProperties
import frontend.Window
import frontend.screen.FinishedScreen
import frontend.screen.PropertiesScreen
import frontend.transitions.EaseScreenSwitchTransition
import frontend.widgets.TextFieldWidget
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseListener : MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
        if (e != null) {
            for (widget in Window.screen.widgets) {
                widget.click(e.x, e.y)
            }

            if (Window.screen is PropertiesScreen) {
                if (e.x > 675 && e.x < (675 + 90) && e.y > 675 && e.y < (675 + 50 + 20)) {
                    var check = true

                    for (widget in Window.screen.widgets) {
                        if (widget is TextFieldWidget) {
                            if (widget.text == "") {
                                widget.error()
                                check = false
                            }
                        }
                    }

                    if (check) {
                        InstallationProperties.desktopLnk = (Window.screen as PropertiesScreen).desktopLnk.checked
                        InstallationProperties.startLnk = (Window.screen as PropertiesScreen).startLnk.checked
                        InstallationProperties.instDir = (Window.screen as PropertiesScreen).dirText.text
                        InstallationProperties.dri1Fil = (Window.screen as PropertiesScreen).dri1FilText.text
                        InstallationProperties.dri2Fil = (Window.screen as PropertiesScreen).dri2FilText.text
                        InstallationProperties.dri1Na = (Window.screen as PropertiesScreen).dri1NaText.text
                        InstallationProperties.dri2Na = (Window.screen as PropertiesScreen).dri2NaText.text
                        InstallationProperties.theme = if ((Window.screen as PropertiesScreen).lightLnk.checked) {
                            "LIGHT"
                        } else {
                            "DARK"
                        }
                        EaseScreenSwitchTransition(FinishedScreen()).start()
                    }
                }
            }

        }
    }

    override fun mousePressed(e: MouseEvent?) {}

    override fun mouseReleased(e: MouseEvent?) {}

    override fun mouseEntered(e: MouseEvent?) {}

    override fun mouseExited(e: MouseEvent?) {}

}