package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "审批学校及学校负责人需提供的信息ApproveSchoolInfo")
public class ApproveSchoolInfo {
    @NotBlank(message="学校代码不能为空")
    @ApiModelProperty(value = "学校代码，非空", example = "xxxxx")
    private String schoolId;

    @Email(message = "学校负责人邮箱错误")
    @ApiModelProperty(value = "学校负责人邮箱，非空", example = "12345678@qq.com")
    private String userEmail;

    @NotNull(message = "审批结果")
    @ApiModelProperty(value = "审批结果，非空", example = "2批准，3驳回")
    private Integer approveResult;

    @NotBlank(message = "审批理由")
    @ApiModelProperty(value = "审批理由，非空", example = "资料审核合格")
    private String approveReason;
}
