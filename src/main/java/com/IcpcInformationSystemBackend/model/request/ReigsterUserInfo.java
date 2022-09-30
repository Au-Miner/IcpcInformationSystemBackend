package com.IcpcInformationSystemBackend.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class ReigsterUserInfo {

    @NotNull(message="用户身份不能为空")
    private int identity;

    @NotBlank(message="用户中文名不能为空")
    private String chiName;

    @NotBlank(message="用户英文名不能为空")
    private String engName;

    @NotBlank(message="用户手机号不能为空")
    private String telephone;

    @Email(message = "用户邮箱错误")
    private String email;

    @NotBlank(message="邮箱验证码不能为空")
    private String emailCode;

    @NotBlank(message="用户入学日期不能为空")
    private String admissionDate;

    @NotBlank(message="用户所属学校社会代码不能为空")
    private String schoolId;

    @NotBlank(message="用户尺码不能为空")
    private String clothSize;
}
