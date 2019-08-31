package com.saveuser.service;

import com.saveuser.entity.OtpDetails;
import com.saveuser.model.OtpResponse;
import com.saveuser.model.RequestData;
import com.saveuser.repository.OtpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {

    private static final Logger log = LoggerFactory.getLogger(OtpService.class);

    @Value("${app.otp-service.expire-time}")
    private Integer expiryTimeMins;

    @Value("${app.otp-provider.url}")
    private String url;

    @Value("${app.otp-provider.api-key}")
    private String api_key;

    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void generateOtp(RequestData requestData){
        RestTemplate restTemplate = new RestTemplate();

        Integer otp= getRandomInteger(999999, 100000);
        String otpUrl=String.format(url,api_key,requestData.getMobileNumber(),otp);

        ResponseEntity<OtpResponse> otpResponse=restTemplate.exchange(otpUrl, HttpMethod.GET, null, OtpResponse.class);
        log.info(otpResponse.toString());
        OtpDetails otpDetails=new OtpDetails(Long.parseLong(requestData.getMobileNumber()), otp, LocalDateTime.now());
        log.info("Otp for {} is {}",otpDetails.getMobileNumber(), otpDetails.getOtp());
        otpRepository.save(otpDetails);
    }

    @Transactional
    public Boolean verifyOtp(RequestData requestData) throws Exception {

        Optional<OtpDetails> otpDetails=otpRepository.findById(Long.parseLong(requestData.getMobileNumber()));
        if(otpDetails.isPresent()){
            LocalDateTime localDateTime=LocalDateTime.now();
            OtpDetails persistOtp=otpDetails.get();
            userService.saveUser(persistOtp.getMobileNumber().toString());
            if(persistOtp.getGenerationTime().plusMinutes(expiryTimeMins).isAfter(localDateTime) && persistOtp.getOtp()==Integer.parseInt(requestData.getOtp())){
                otpRepository.delete(persistOtp);
                return true;
            }
            else
                return true;
        }else{
            throw new Exception("Record not present");
        }

    }

    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }


}
