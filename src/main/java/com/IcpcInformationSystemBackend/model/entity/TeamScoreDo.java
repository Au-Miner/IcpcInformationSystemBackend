package com.IcpcInformationSystemBackend.model.entity;

public class TeamScoreDo extends TeamScoreDoKey {
    private String teamCertificate;

    private String rnk;

    private String photos;

    private String chiMedal;

    private String engMedal;

    public String getTeamCertificate() {
        return teamCertificate;
    }

    public void setTeamCertificate(String teamCertificate) {
        this.teamCertificate = teamCertificate == null ? null : teamCertificate.trim();
    }

    public String getRnk() {
        return rnk;
    }

    public void setRnk(String rnk) {
        this.rnk = rnk == null ? null : rnk.trim();
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos == null ? null : photos.trim();
    }

    public String getChiMedal() {
        return chiMedal;
    }

    public void setChiMedal(String chiMedal) {
        this.chiMedal = chiMedal == null ? null : chiMedal.trim();
    }

    public String getEngMedal() {
        return engMedal;
    }

    public void setEngMedal(String engMedal) {
        this.engMedal = engMedal == null ? null : engMedal.trim();
    }
}