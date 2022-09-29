package com.IcpcInformationSystemBackend.model.entity;

public class TeamDo {
    private String teamId;

    private String chiTeamName;

    private String engTeamName;

    private String schoolId;

    private String member1Id;

    private String member2Id;

    private String member3Id;

    private String coach1Id;

    private String coach2Id;

    private String competitionId;

    private Integer type;

    private Integer state;

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

    public String getMember1Id() {
        return member1Id;
    }

    public void setMember1Id(String member1Id) {
        this.member1Id = member1Id == null ? null : member1Id.trim();
    }

    public String getMember2Id() {
        return member2Id;
    }

    public void setMember2Id(String member2Id) {
        this.member2Id = member2Id == null ? null : member2Id.trim();
    }

    public String getMember3Id() {
        return member3Id;
    }

    public void setMember3Id(String member3Id) {
        this.member3Id = member3Id == null ? null : member3Id.trim();
    }

    public String getCoach1Id() {
        return coach1Id;
    }

    public void setCoach1Id(String coach1Id) {
        this.coach1Id = coach1Id == null ? null : coach1Id.trim();
    }

    public String getCoach2Id() {
        return coach2Id;
    }

    public void setCoach2Id(String coach2Id) {
        this.coach2Id = coach2Id == null ? null : coach2Id.trim();
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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