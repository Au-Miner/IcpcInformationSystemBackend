package com.IcpcInformationSystemBackend.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SchoolIdResponse {
    private String schoolId;
    private String chiSchoolName;
}
