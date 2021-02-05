package backend.processes.installation

import backend.InstallationProperties
import backend.Process
import frontend.Window
import org.json.simple.JSONObject
import java.io.File
import java.lang.Exception

class CreateConfigurationProcess : Process() {

    /**
     * Always true
     */
    override var test: Boolean = true

    /**
     * Current status of the process
     */
    override var status: Int = 0

    override fun run() {

        try {
            val file = File("${InstallationProperties.instDir}config.json")
            val json = JSONObject()

            json["drive1_filter"] = InstallationProperties.dri1Fil
            json["drive2_filter"] = InstallationProperties.dri2Fil
            json["drive1_name"] = InstallationProperties.dri1Na
            json["drive2_name"] = InstallationProperties.dri2Na

            when(InstallationProperties.theme) {
                "LIGHT" -> {
                    json["color_text"] = "#000000"
                    json["color_bg"] = "#FFFFFF"
                }
                "DARK" -> {
                    json["color_text"] = "#FFFFFF"
                    json["color_bg"] = "#000000"
                }
            }

            file.writeText(json.toJSONString())

            if(file.readText() != "") {
                status = 1
            }
        }catch (e: Exception) {
            HMDInstaller.showError(403)
        }

    }

}