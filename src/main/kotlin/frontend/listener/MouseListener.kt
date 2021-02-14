package frontend.listener

import backend.InstallationProperties
import backend.UninstallationProperties
import frontend.Window
import frontend.screen.installation.InstallationFinishedScreen
import frontend.screen.installation.InstallationPropertiesScreen
import frontend.screen.uninstallation.UninstallationFinishedScreen
import frontend.screen.uninstallation.UninstallationPropertiesScreen
import frontend.transitions.EaseScreenSwitchTransition
import frontend.widgets.DirectoryChooserWidget
import frontend.widgets.TextFieldWidget
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class MouseListener : MouseListener {

    override fun mouseClicked(e: MouseEvent?) {
        if (e != null) {
            for (widget in Window.screen.widgets) {
                widget.click(e.x, e.y)
            }

            if (Window.screen is InstallationPropertiesScreen) {
                if (e.x > 675 && e.x < (675 + 90) && e.y > 675 && e.y < (675 + 50 + 20)) {
                    var check = true

                    for (widget in Window.screen.widgets) {
                        if (widget is TextFieldWidget) {
                            if (widget.text == "") {
                                widget.error()
                                check = false
                            }
                        } else if (widget is DirectoryChooserWidget) {
                            if (widget.text == "") {
                                widget.error()
                                check = false
                            }
                        }
                    }

                    if (check) {
                        InstallationProperties.desktopLnk =
                            (Window.screen as InstallationPropertiesScreen).desktopLnk.checked
                        InstallationProperties.startLnk =
                            (Window.screen as InstallationPropertiesScreen).startLnk.checked
                        InstallationProperties.instDir = (Window.screen as InstallationPropertiesScreen).dirText.text
                        InstallationProperties.dri1Fil =
                            (Window.screen as InstallationPropertiesScreen).dri1FilText.text
                        InstallationProperties.dri2Fil =
                            (Window.screen as InstallationPropertiesScreen).dri2FilText.text
                        InstallationProperties.dri1Na = (Window.screen as InstallationPropertiesScreen).dri1NaText.text
                        InstallationProperties.dri2Na = (Window.screen as InstallationPropertiesScreen).dri2NaText.text
                        InstallationProperties.theme =
                            if ((Window.screen as InstallationPropertiesScreen).lightLnk.checked) {
                                "LIGHT"
                            } else {
                                "DARK"
                            }
                        EaseScreenSwitchTransition(InstallationFinishedScreen()).start()
                    }
                }
            } else if (Window.screen is UninstallationPropertiesScreen) {
                if (e.x > 675 && e.x < (675 + 90) && e.y > 675 && e.y < (675 + 50 + 20)) {
                    var check = true

                    for (widget in Window.screen.widgets) {
                        if (widget is DirectoryChooserWidget) {
                            if (widget.text == "") {
                                widget.error()
                                check = false
                            }
                        }
                    }

                    if (check) {
                        UninstallationProperties.instDir =
                            (Window.screen as UninstallationPropertiesScreen).dirText.text
                        EaseScreenSwitchTransition(UninstallationFinishedScreen()).start()
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