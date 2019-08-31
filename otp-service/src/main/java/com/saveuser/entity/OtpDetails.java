package com.saveuser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_details")
public class OtpDetails {

    @Id
    @Column(name = "mobile_number")
    private Long mobileNumber;

    private Integer otp;

    private LocalDateTime generationTime;

    public OtpDetails() {
    }

    public OtpDetails(Long mobileNumber, Integer otp, LocalDateTime generationTime) {
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.generationTime = generationTime;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public LocalDateTime getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(LocalDateTime generationTime) {
        this.generationTime = generationTime;
    }
}
