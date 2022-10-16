package com.IcpcInformationSystemBackend.model.entity;

public class TeamRankDo extends TeamRankDoKey {
    private String teamCertificate;

    private Integer rnk;

    private String photos;

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