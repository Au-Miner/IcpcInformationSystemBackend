package com.IcpcInformationSystemBackend.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "注册用户需要提供的信息ReigsterUserInfo")
public class ReigsterUserInfo {

    @Email(message = "用户邮箱错误")
    @ApiModelProperty(value = "用户邮箱，非空", example = "123456@qq.com")
    private String userEmail;

    @NotBlank(message="学校负责人身份证号不能为空")
    @ApiModelProperty(value = "学校负责人身份证号，非空", example = "例如320301200112131234")
    private String idCard;

    @NotNull(message="用户身份不能为空")
    @ApiModelProperty(value = "用户身份（只能1或者2），非空", example = "1选手2教练")
    private Integer identity;

    @NotBlank(message="用户中文名不能为空")
    @ApiModelProperty(value = "用户中文名，非空", example = "王启隆")
    private String chiName;

    @NotBlank(message="用户英文名不能为空")
    @ApiModelProperty(value = "用户英文名，非空", example = "Wang Qilong")
    private String engName;

    @NotBlank(message="用户手机号不能为空")
    @ApiModelProperty(value = "用户手机号，非空", example = "例如18800311234")
    private String telephone;

    @NotBlank(message="邮箱验证码不能为空")
    @ApiModelProperty(value = "邮箱验证码，非空", example = "例如123456")
    private String emailCode;

    @NotBlank(message="用户入学日期不能为空")
    @ApiModelProperty(value = "用户入学时间，非空", example = "2022-09")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8")
    private String admissionDate;

    @NotBlank(message="用户所属学校代码不能为空")
    @ApiModelProperty(value = "学校代码，非空", example = "例如12345")
    private String schoolId;

    @NotBlank(message="用户尺码不能为空")
    @ApiModelProperty(value = "衣服尺寸，非空", example = "XXL")
    private String clothSize;

    @NotBlank
    @ApiModelProperty(value = "用户登陆密码，非空", example = "wql011213")
    private String passwd;
}
