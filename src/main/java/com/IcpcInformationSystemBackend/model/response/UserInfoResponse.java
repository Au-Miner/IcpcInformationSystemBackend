package com.IcpcInformationSystemBackend.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserInfoResponse {
    private String userEmail;
    private String chiName;
    private String engName;
    private String telephone;
    private String emailCode;
    private String admissionDate;
    private String schoolId;
    private String chiSchoolName;
    private String clothSize;
    private Integer userState;
    private String idCard;
    private Integer identity;
}
