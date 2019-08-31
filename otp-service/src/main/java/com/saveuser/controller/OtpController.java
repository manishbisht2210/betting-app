package com.saveuser.controller;

import com.saveuser.model.RequestData;
import com.saveuser.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v1/one-time-password/")
@RestController
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("sendOtp")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateAndSendOtp(@RequestBody @Validated RequestData requestData ){
        otpService.generateOtp(requestData);
        return "created";
    }

    @PostMapping("validateOtp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> validateOtp(@RequestBody @Validated RequestData requestData ){
        try {
            if(otpService.verifyOtp(requestData)){
                return new ResponseEntity<String>(HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            if(e.getMessage().equals("Record not present")){
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("resendOtp")
    public String resendOtp(){
        return "";
    }


}
