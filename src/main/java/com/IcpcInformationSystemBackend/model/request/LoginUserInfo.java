package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "登录用户需要提供的信息")
public class LoginUserInfo {
    @NotBlank(message="用户邮箱不能为空")
    @ApiModelProperty(value = "用户邮箱，非空", example = "xxxxx")
    private String key;

    @NotBlank(message="用户密码不能为空")
    @ApiModelProperty(value = "用户密码，非空",  example = "wql011213")
    private String password;

    @NotNull(message="用户身份不能为空")
    @ApiModelProperty(value = "用户身份，非空", example = "1或者2或者3或者4")
    private Integer identity;
}
