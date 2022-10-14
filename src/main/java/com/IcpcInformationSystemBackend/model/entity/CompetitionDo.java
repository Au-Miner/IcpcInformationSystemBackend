package com.IcpcInformationSystemBackend.model.entity;

import java.util.Date;

public class CompetitionDo {
    private String competitionId;

    private String competitionChiName;

    private String competitionEngName;

    private String schoolId;

    private String builderEmail;

    private String heldPosition;

    private Date registrationStartTime;

    private Date registrationEndTime;

    private Date payStartTime;

    private Date payEndTime;

    private Date competitionStartTime;

    private String duration;

    private String competitionIntroduction;

    private Integer competitionState;

    private String approveReason;

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId == null ? null : competitionId.trim();
    }

    public String getCompetitionChiName() {
        return competitionChiName;
    }

    public void setCompetitionChiName(String competitionChiName) {
        this.competitionChiName = competitionChiName == null ? null : competitionChiName.trim();
    }

    public String getCompetitionEngName() {
        return competitionEngName;
    }

    public void setCompetitionEngName(String competitionEngName) {
        this.competitionEngName = competitionEngName == null ? null : competitionEngName.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getBuilderEmail() {
        return builderEmail;
    }

    public void setBuilderEmail(String builderEmail) {
        this.builderEmail = builderEmail == null ? null : builderEmail.trim();
    }

    public String getHeldPosition() {
        return heldPosition;
    }

    public void setHeldPosition(String heldPosition) {
        this.heldPosition = heldPosition == null ? null : heldPosition.trim();
    }

    public Date getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(Date registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public Date getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(Date registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    public Date getPayStartTime() {
        return payStartTime;
    }

    public void setPayStartTime(Date payStartTime) {
        this.payStartTime = payStartTime;
    }

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public Date getCompetitionStartTime() {
        return competitionStartTime;
    }

    public void setCompetitionStartTime(Date competitionStartTime) {
        this.competitionStartTime = competitionStartTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public String getCompetitionIntroduction() {
        return competitionIntroduction;
    }

    public void setCompetitionIntroduction(String competitionIntroduction) {
        this.competitionIntroduction = competitionIntroduction == null ? null : competitionIntroduction.trim();
    }

    public Integer getCompetitionState() {
        return competitionState;
    }

    public void setCompetitionState(Integer competitionState) {
        this.competitionState = competitionState;
    }

    public String getApproveReason() {
        return approveReason;
    }

    public void setApproveReason(String approveReason) {
        this.approveReason = approveReason == null ? null : approveReason.trim();
    }
}