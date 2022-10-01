package com.IcpcInformationSystemBackend.model.entity;

public class CompetitionDo {
    private String competitionId;

    private String competitionChiName;

    private String competitionEngName;

    private String schoolId;

    private String builderEmail;

    private String heldPosition;

    private String registrationStartTime;

    private String registrationEndTime;

    private String competitionStartTime;

    private Integer duration;

    private String competitionIntroduction;

    private Integer state;

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

    public String getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(String registrationStartTime) {
        this.registrationStartTime = registrationStartTime == null ? null : registrationStartTime.trim();
    }

    public String getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(String registrationEndTime) {
        this.registrationEndTime = registrationEndTime == null ? null : registrationEndTime.trim();
    }

    public String getCompetitionStartTime() {
        return competitionStartTime;
    }

    public void setCompetitionStartTime(String competitionStartTime) {
        this.competitionStartTime = competitionStartTime == null ? null : competitionStartTime.trim();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCompetitionIntroduction() {
        return competitionIntroduction;
    }

    public void setCompetitionIntroduction(String competitionIntroduction) {
        this.competitionIntroduction = competitionIntroduction == null ? null : competitionIntroduction.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}