package com.IcpcInformationSystemBackend.model.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String userEmail;
    private String chiName;
    private String engName;
    private String telephone;
    private String admissionDate;
    private String schoolId;
    private String chiSchoolName;
    private String clothSize;
    private Integer userState;
    private String idCard;
    private Integer identity;
    private String token;
}
