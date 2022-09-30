package com.IcpcInformationSystemBackend.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterSchoolInfo {

    @NotBlank(message="学校社会代码不能为空")
    private String schoolId;

    @NotBlank(message="学校中文名不能为空")
    private String chiSchoolName;

    @NotBlank(message="学校英文名不能为空")
    private String engSchoolName;

    @NotBlank(message="学校校徽不能为空")
    private String schoolImg;

    @NotBlank(message="学校位置不能为空")
    private String schoolPosition;

    @NotBlank(message="学校负责人中文名不能为空")
    private String chiName;

    @NotBlank(message="学校负责人英文名不能为空")
    private String engName;

    @NotBlank(message="学校负责人手机号不能为空")
    private String telephone;

    @Email(message = "学校负责人邮箱错误")
    private String email;

    @NotBlank(message="邮箱验证码不能为空")
    private String emailCode;

    @NotBlank(message="学校负责人尺码不能为空")
    private String clothSize;
}
