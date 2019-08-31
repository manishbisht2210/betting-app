import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  StatusBar ,
  TouchableOpacity
} from 'react-native';

import Logo from '../components/Logo';
import Form from '../components/Form';

import {Actions} from 'react-native-router-flux';
import QRCode from 'react-native-qrcode';
import { encrypt, decrypt } from 'react-native-simple-encryption';

export default class Qrgenerate extends Component<{}> {

  goBack() {
      Actions.pop();
  }
	
  constructor() {
	super();
	   this.state = {
      inputValue: '9769399720',
      // Default Value of the TextInput
      valueForQRCode: '9769399720'
      // Default value for the QR Code
  };
  
  this.getTextInputValue();
  };
  
	getTextInputValue(){
    // Function to get the value from input
    // and Setting the value to the QRCode
	let enc =encrypt('secretkey123', this.state.valueForQRCode);
this.setState({ valueForQRCode: enc });
//return enc
 };

  
	render() {
		return(
		<View style={styles.QRContainer}>
			
		<QRCode		
		  value={this.state.valueForQRCode}
		
          //Setting the value of QRCode
          size={250}
          //Size of QRCode
          bgColor="#000"
          //Backgroun Color of QRCode
          fgColor="#fff"
          //Front Color of QRCode
        />
		<Text style={styles.QRTextStyle}>Please use the above QR to Scan at Kiosk</Text>
			</View>	
			)
	}
}

const styles = StyleSheet.create({
  container : {
    backgroundColor:'#455a64',
    flex: 1,
    alignItems:'center',
    justifyContent :'center'
  },
  QRContainer: {
    flex: 1,
    margin: 10,
    alignItems: 'center',
    paddingTop: 40,
  },
  signupTextCont : {
  	flexGrow: 1,
    alignItems:'flex-end',
    justifyContent :'center',
    paddingVertical:16,
    flexDirection:'row'
  },
  signupText: {
  	color:'rgba(255,255,255,0.6)',
  	fontSize:16
  },
  signupButton: {
  	color:'#ffffff',
  	fontSize:16,
  	fontWeight:'500'
  },
  QRTextStyle: {
    color: '#455a64',
    textAlign: 'center',
    fontSize: 18,
  }
});
