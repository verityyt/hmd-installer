const {app, BrowserWindow} = require("electron")
const electronLocalshortcut = require('electron-localshortcut');

function createWindow() {
    console.log("Creating browser instance....")

    const window = new BrowserWindow({
        width: 1280,
        height: 720,
        webPreferences: {
            nodeIntegration: false,
            allowRunningInsecureContent: true,
            contextIsolation: false
        }
    })

    console.log("Loading url...")

    window.loadURL("http://localhost:3000/")

    console.log("Configuring electron...")

    window.setMenuBarVisibility(false)

    electronLocalshortcut.register(window, 'F5', () => {
        window.reload()
    });

    console.log("Electron ready!")
}

app.whenReady().then(createWindow)