package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "发送邮件时需要提供的信息")
public class EmailMessageInfo {
    @NotBlank(message="邮箱地址不能为空")
    @ApiModelProperty(value = "用户邮箱，非空", example = "xxxxx")
    private String email;

    @NotBlank(message="用户密码不能为空")
    @ApiModelProperty(value = "信息内容，非空",  example = "审批已通过")
    private String message;
}
