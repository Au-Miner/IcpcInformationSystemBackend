package com.IcpcInformationSystemBackend.model.entity;

import java.util.Date;

public class EmailCodeDo {
    private String userEmail;

    private String verificationCode;

    private Date timeOfCode;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
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