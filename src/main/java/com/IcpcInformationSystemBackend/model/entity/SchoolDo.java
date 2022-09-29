package com.IcpcInformationSystemBackend.model.entity;

public class SchoolDo {
    private String schoolId;

    private String chiSchoolName;

    private String engSchoolName;

    private String schoolCode;

    private String schoolImg;

    private Integer state;

    private String schoolPosition;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getChiSchoolName() {
        return chiSchoolName;
    }

    public void setChiSchoolName(String chiSchoolName) {
        this.chiSchoolName = chiSchoolName == null ? null : chiSchoolName.trim();
    }

    public String getEngSchoolName() {
        return engSchoolName;
    }

    public void setEngSchoolName(String engSchoolName) {
        this.engSchoolName = engSchoolName == null ? null : engSchoolName.trim();
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public String getSchoolImg() {
        return schoolImg;
    }

    public void setSchoolImg(String schoolImg) {
        this.schoolImg = schoolImg == null ? null : schoolImg.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSchoolPosition() {
        return schoolPosition;
    }

    public void setSchoolPosition(String schoolPosition) {
        this.schoolPosition = schoolPosition == null ? null : schoolPosition.trim();
    }
}