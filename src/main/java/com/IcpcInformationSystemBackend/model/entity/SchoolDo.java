package com.IcpcInformationSystemBackend.model.entity;

public class SchoolDo {
    private String schoolId;

    private String chiSchoolName;

    private String engSchoolName;

    private String schoolImg;

    private Integer schoolState;

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

    public String getSchoolImg() {
        return schoolImg;
    }

    public void setSchoolImg(String schoolImg) {
        this.schoolImg = schoolImg == null ? null : schoolImg.trim();
    }

    public Integer getSchoolState() {
        return schoolState;
    }

    public void setSchoolState(Integer schoolState) {
        this.schoolState = schoolState;
    }

    public String getSchoolPosition() {
        return schoolPosition;
    }

    public void setSchoolPosition(String schoolPosition) {
        this.schoolPosition = schoolPosition == null ? null : schoolPosition.trim();
    }
}