package frontend.transitions

import frontend.Window
import frontend.utils.Screen

class EaseScreenSwitchTransition(private val newNextScreen: Screen?) : Transition() {

    private var finished = false

    override fun start() {
        val tick = 5
        var speed: Long = 1

        Thread {
            while (!finished) {
                if (Window.nextScreen != null) {
                    if (Window.nextScreen!!.originX != (-799 + tick)) {
                        Thread.sleep(speed)

                        Window.screen.originX -= tick

                        for(widget in Window.screen.widgets) {
                            widget.x -= tick
                        }

                        if (Window.nextScreen != null && Window.nextScreen!!.originX >= 0) {
                            speed = 6
                        }

                        if (Window.nextScreen != null && Window.nextScreen!!.originX >= -25) {
                            speed = 2
                        }

                        if (Window.nextScreen != null && Window.nextScreen!!.originX >= -50) {
                            speed = 1
                        }

                        if (Window.nextScreen != null && Window.nextScreen!!.originX <= -725) {
                            speed = 2
                        }

                        if (Window.nextScreen != null && Window.nextScreen!!.originX <= -775) {
                            speed = 6
                        }

                    } else {
                        Thread.currentThread().interrupt()
                    }
                }
            }
        }.start()

        Thread {
            while (!finished) {
                if (Window.nextScreen != null) {
                        if (Window.nextScreen!!.originX != (0 + tick)) {
                            Thread.sleep(speed)

                            Window.nextScreen!!.originX -= tick

                            for(widget in Window.screen.widgets) {
                                widget.x -= tick
                            }

                            if (Window.nextScreen != null && Window.nextScreen!!.originX <= 800) {
                                speed = 6
                            }

                            if (Window.nextScreen != null && Window.nextScreen!!.originX <= 750) {
                                speed = 2
                            }

                            if (Window.nextScreen != null && Window.nextScreen!!.originX <= 725) {
                                speed = 1
                            }

                            if (Window.nextScreen != null && Window.nextScreen!!.originX <= 25) {
                                speed = 2
                            }

                            if (Window.nextScreen != null && Window.nextScreen!!.originX <= 75) {
                                speed = 6
                            }

                        } else {
                            Window.screen = Window.nextScreen!!
                            if(newNextScreen != null) {
                                Window.nextScreen = newNextScreen
                            }
                            finished = true
                            Window.screen.afterSwitch()
                            Thread.currentThread().interrupt()
                        }
                }
            }
        }.start()
    }

}