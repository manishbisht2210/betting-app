const electron = require('electron');
const isDev = require('electron-is-dev');
const url = require('url');
const path = require('path');

const {app, BrowserWindow,BrowserView, Menu, ipcMain} = electron;

//SET ENV
process.env.NODE_ENV='production1';

let mainWindow;
let betWindow;
let paymentsWindow;
//const reactURL = mainWindow.loadURL(isDev ? 'http://localhost:3000' : 'file://${path.join(__dirname, "../build/index.html")}');
const reactURL = 'http://localhost:3000';
const userId = '9769399720';

//Listen for app to be ready
app.on('ready', function(){

    //Create a Browser Window
    mainWindow = new BrowserWindow({
        width: 720,
        height: 700,
        title: 'Place Your Bet'
    });

    //Load html into window
    mainWindow.loadURL(url.format({
        pathname: path.join(__dirname, 'mainWindow.html'),
        protocol: 'file:',
        slashes: true
    }));

    // Quit app when closed
    mainWindow.on('closed', function(){
        app.quit();
    })

    //Build menu from tempalate
    const mainMenu = Menu.buildFromTemplate(mainMenuTemplate);
    //Insert the menu
    Menu.setApplicationMenu(mainMenu); 

    // let view = new BrowserView({
    //     webPreferences: {
    //       nodeIntegration: false
    //     }
    //   })
    //   mainWindow.setBrowserView(view)
    // view.setBounds({ x: 0, y: 0, width: 300, height: 300 })
    // view.webContents.loadURL(reactURL);
    // view.webContents.ipcMain.on();
});

//Handle Add Bet
function createAddBetWindow()
{
    betWindow = new BrowserWindow({
        width: 318,
        height: 360,
        title: 'Verify User'
    });

    //Load html into window
    betWindow.loadURL(reactURL);

    //Garbage collection handle
    betWindow.on('close', function(){
        mainWindow.webContents.send('catch:scanned', userId);
        betWindow = null;
    });
}

//Handle Add Bet
function createPaymentsView(paymentsURL)
{
    paymentsWindow = new BrowserWindow({
        width: 600,
        height: 500,
        title: 'Complete Payment'
    });

    //Load html into window
    console.log('paymentsURL : ' + paymentsURL);
    paymentsWindow.loadURL(paymentsURL);

    //Garbage collection handle
    paymentsWindow.on('close', function(){
        paymentsWindow = null;
        mainWindow.webContents.send('payment:complete');
    });

}

function createWebview(){
     console.log("Reached main.js");
     //mainWindow.webContents.send('show:scanner', reactURL);
     createAddBetWindow(reactURL);
     betWindow.webContents.send("show:scanner");
}

function scannedQRCode(result){
    console.log("Scanned :" + result);
    mainWindow.webContents.send('catch:scanned', reactURL);

    //Call MS for sending the scanned value to OTP Service
}

//Catch the Bet
ipcMain.on('bet:add', function(e, item){
    console.log(item);
    mainWindow.webContents.send('bet:add', JSON.stringify(item));
});

//Catch the Bet
ipcMain.on('open:payment', function(e, item){
    console.log(item);
    createPaymentsView(item);
});


// show scanner event
ipcMain.on('show:scanner', function(e){
    createWebview();
});

ipcMain.on('catch:scanned', function(e, data){
    console.log("In main.js", data);
});

// Create Menu Template
// darwin for mac, win32 for windows
const mainMenuTemplate = [
    {
        label: 'Bet your luck'
    }

];
// if mac then add empty object to menu. Becoz otherwise u will see Electron instead of File
if(process.platform == 'darwin'){
    mainMenuTemplate.unshift({});
}

// Add developer tools if not in production
if(process.env.NODE_ENV !== 'production')
{
    mainMenuTemplate.push({
        label: 'Developer Tools',
        submenu: [
            {
                label: 'Toggle DevTools', 
                accelerator: process.platform == 'darwin' ? 'Command+I' : 
                'Ctrl+I',
                click(item, focusedWindow){
                    focusedWindow.toggleDevTools();
                }
            },
            {
              role: 'reload'
            }
        ]
    })
}


