package com.IcpcInformationSystemBackend.model.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "注册学校需要提供的信息RegisterSchoolInfo")
public class RegisterSchoolInfo {

    @NotBlank(message="学校代码不能为空")
    @ApiModelProperty(value = "学校代码，非空", example = "xxxxx")
    private String schoolId;

    @NotBlank(message="学校中文名不能为空")
    @ApiModelProperty(value = "学校中文名，非空",  example = "上海大学")
    private String chiSchoolName;

    @NotBlank(message="学校英文名不能为空")
    @ApiModelProperty(value = "学校英文名，非空", example = "Shanghai University")
    private String engSchoolName;

    @NotBlank(message="学校照片地址不能为空")
    @ApiModelProperty(value = "学校照片地址，非空", example = "C:/")
    private String schoolImg;

    @NotBlank(message="学校位置不能为空")
    @ApiModelProperty(value = "学校位置，非空", example = "上海市宝山区")
    private String schoolPosition;

    @NotBlank(message="学校负责人身份证号不能为空")
    @ApiModelProperty(value = "学校负责人身份证号，非空", example = "例如320301200112131234")
    private String idCard;

    @NotBlank(message="学校负责人中文名不能为空")
    @ApiModelProperty(value = "学校负责人中文名，非空", example = "王启隆")
    private String chiName;

    @NotBlank(message="学校负责人英文名不能为空")
    @ApiModelProperty(value = "学校负责人英文名，非空", example = "Wang Qilong")
    private String engName;

    @NotBlank(message="学校负责人手机号不能为空")
    @ApiModelProperty(value = "学下负责人手机号，非空", example = "xxxxx")
    private String telephone;

    @Email(message = "学校负责人邮箱错误")
    @ApiModelProperty(value = "学校负责人邮箱，非空", example = "12345678@qq.com")
    private String userEmail;

    @NotBlank(message="邮箱验证码不能为空")
    @ApiModelProperty(value = "邮箱验证码，非空", example = "12ed5f")
    private String emailCode;

    @NotBlank(message="学校负责人尺码不能为空")
    @ApiModelProperty(value = "衣服尺寸，非空", example = "XXL")
    private String clothSize;

    @NotBlank
    @ApiModelProperty(value = "用户登陆密码，非空", example = "wql011213")
    private String passwd;
}
