package com.IcpcInformationSystemBackend.model.entity;

public class UserCompetitionDo extends UserCompetitionDoKey {
    private String teamId;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }
}