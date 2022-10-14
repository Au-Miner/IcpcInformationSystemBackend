package com.IcpcInformationSystemBackend.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SchoolInfoResponse {
    private String schoolId;
    private String chiSchoolName;
    private String engSchoolName;
    private String schoolImg;
    private String schoolPosition;
    private String chiName;
    private String engName;
    private String telephone;
    private String userEmail;
    private String clothSize;
    private Integer schoolState;
}
