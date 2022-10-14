package com.IcpcInformationSystemBackend.model.entity;

public class TeamRankDo {
    private String teamId;

    private String teamCertificate;

    private Integer rnk;

    private String photos;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public String getTeamCertificate() {
        return teamCertificate;
    }

    public void setTeamCertificate(String teamCertificate) {
        this.teamCertificate = teamCertificate == null ? null : teamCertificate.trim();
    }

    public Integer getRnk() {
        return rnk;
    }

    public void setRnk(Integer rnk) {
        this.rnk = rnk;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos == null ? null : photos.trim();
    }
}