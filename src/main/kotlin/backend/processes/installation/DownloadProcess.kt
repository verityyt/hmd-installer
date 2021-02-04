package backend.processes.installation

import backend.Process
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class DownloadProcess : Process() {

    /**
     * Always true
     */
    override var test: Boolean = true

    /**
     * Current status of the process
     */
    override var status: Int = 0

    /**
     * Target location of the zip file which has to be downloaded from the latest github release of the hardware-monitoring display
     */
    private var file = File("files\\current-version.zip")

    override fun run() {
        Thread {
            if(file.exists()) {
                println("[DownloadProcess] Deleting old installation version...")
                file.deleteRecursively()
            }

            println("[DownloadProcess] Downloading new installation version...")

            val url =
                "https://github.com/verityyt/hardware-monitoring-display/releases/latest/download/installer-download.zip"

            try {
                val input = BufferedInputStream(URL(url).openStream())
                val output = FileOutputStream("files\\current-version.zip")
                val dataBuffer = ByteArray(1024)
                var bytesRead = 0
                while (input.read(dataBuffer, 0, 1024).also { bytesRead = it } !== -1) {
                    output.write(dataBuffer, 0, bytesRead)
                }
            } catch (e: Exception) {
                HMDInstaller.showError(408)
            }

            if (file.exists()) {
                println("[DownloadProcess] File successfully downloaded!")
                status = 1
            } else {
                HMDInstaller.showError(400,"Process 'DownloadProcess' failed")
            }
        }.start()
    }

}