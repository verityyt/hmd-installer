package backend.processes.installation

import backend.Process
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.net.URL

class DownloadProcess : Process() {

    override val test: Boolean = true

    override fun run() {

        val url = "https://github.com/verityyt/hardware-monitoring-display/releases/latest/download/installer-download.zip"

        try {
            val input = BufferedInputStream(URL(url).openStream())
            val output = FileOutputStream("files\\current-version.zip")
            val dataBuffer = ByteArray(1024)
            var bytesRead = 0
            while (input.read(dataBuffer, 0, 1024).also { bytesRead = it } !== -1) {
                output.write(dataBuffer, 0, bytesRead)
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }

    }

}