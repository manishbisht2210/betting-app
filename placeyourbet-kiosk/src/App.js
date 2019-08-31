import React, { Component } from 'react'
import ReactDOM from 'react-dom';
import QrReader from 'react-qr-reader'
import './App.css';
import { BrowserWindow } from "electron"



class App extends Component {
  state = {
    result: 'No result'
  }
 
  handleScan = data => {
    if (data) {
      this.setState({
        result: data
      })
     // ipcRenderer.send("catch:scanned", data);
     //BrowserWindow.getFocusedWindow().webContents.send("catch:scanned", data);
      const element = (
        <div>
          <h1>QR Code scanned successfully kindly close the Window</h1>
        </div>
      );
      ReactDOM.render(element, document.getElementById('main'));
    }
  }
  handleError = err => {
    console.error(err)
  }

  // handleScan(data)
  // {
  //   if (data) {
  //     ipcRenderer.sendToHost("catch:scanned", data);
  //   }
  // }

  render() {
    return (
      <html>
        <head>
        </head>
        <body>
          <div id="main" className="scanner-container">
            <QrReader
              delay={300}
              onError={this.handleError}
              onScan={this.handleScan}
              style={{ width: '300px', height: '300px', position: 'relative' }}
            />
            {/* <input type='text' id="scannedValue" value={this.state.result}/> */}
          </div>
      </body>
      </html>
    )
  }
}

export default App;
