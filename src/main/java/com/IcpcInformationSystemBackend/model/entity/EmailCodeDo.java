package com.IcpcInformationSystemBackend.model.entity;

import java.util.Date;

public class EmailCodeDo {
    private String email;

    private String verificationCode;

    private Date timeOfCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode == null ? null : verificationCode.trim();
    }

    public Date getTimeOfCode() {
        return timeOfCode;
    }

    public void setTimeOfCode(Date timeOfCode) {
        this.timeOfCode = timeOfCode;
    }
}