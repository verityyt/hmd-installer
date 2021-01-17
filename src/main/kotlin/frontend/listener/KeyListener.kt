package frontend.listener

import frontend.Window
import java.awt.event.KeyEvent

class KeyListener : java.awt.event.KeyListener {

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            for (widget in Window.screen.widgets) {
                widget.type(e)
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {}

}