package com.IcpcInformationSystemBackend.model.entity;

public class TeamDo {
    private String teamId;

    private String chiTeamName;

    private String engTeamName;

    private String schoolId;

    private String member1Email;

    private String member2Email;

    private String member3Email;

    private String coach1Email;

    private String coach2Email;

    private String competitionId;

    private Integer type;

    private Integer teamState;

    private String reason;

    private Integer needTeamCertificate;

    private String competitionPosition;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public String getChiTeamName() {
        return chiTeamName;
    }

    public void setChiTeamName(String chiTeamName) {
        this.chiTeamName = chiTeamName == null ? null : chiTeamName.trim();
    }

    public String getEngTeamName() {
        return engTeamName;
    }

    public void setEngTeamName(String engTeamName) {
        this.engTeamName = engTeamName == null ? null : engTeamName.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getMember1Email() {
        return member1Email;
    }

    public void setMember1Email(String member1Email) {
        this.member1Email = member1Email == null ? null : member1Email.trim();
    }

    public String getMember2Email() {
        return member2Email;
    }

    public void setMember2Email(String member2Email) {
        this.member2Email = member2Email == null ? null : member2Email.trim();
    }

    public String getMember3Email() {
        return member3Email;
    }

    public void setMember3Email(String member3Email) {
        this.member3Email = member3Email == null ? null : member3Email.trim();
    }

    public String getCoach1Email() {
        return coach1Email;
    }

    public void setCoach1Email(String coach1Email) {
        this.coach1Email = coach1Email == null ? null : coach1Email.trim();
    }

    public String getCoach2Email() {
        return coach2Email;
    }

    public void setCoach2Email(String coach2Email) {
        this.coach2Email = coach2Email == null ? null : coach2Email.trim();
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId == null ? null : competitionId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTeamState() {
        return teamState;
    }

    public void setTeamState(Integer teamState) {
        this.teamState = teamState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getNeedTeamCertificate() {
        return needTeamCertificate;
    }

    public void setNeedTeamCertificate(Integer needTeamCertificate) {
        this.needTeamCertificate = needTeamCertificate;
    }

    public String getCompetitionPosition() {
        return competitionPosition;
    }

    public void setCompetitionPosition(String competitionPosition) {
        this.competitionPosition = competitionPosition == null ? null : competitionPosition.trim();
    }
}