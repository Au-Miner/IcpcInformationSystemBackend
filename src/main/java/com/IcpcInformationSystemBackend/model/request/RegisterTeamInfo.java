package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "创建小组时需要提供的信息")
public class RegisterTeamInfo {
    @ApiModelProperty(value = "队伍id号，如果为空表示申请创建队伍，如果非空表示重新申请创建队伍", example = "xxxxx")
    private String teamId;

    @NotBlank
    @Length(max = 30, message = "长度过长！")
    @ApiModelProperty(value = "队伍中文名，非空", example = "还是你懂得多啊")
    private String chiTeamName;

    @NotBlank
    @ApiModelProperty(value = "队伍英文名，非空", example = "wow, you know better than me")
    private String engTeamName;

    @NotBlank
    @ApiModelProperty(value = "学校id，非空", example = "xxxxx")
    private String schoolId;

    @Email(message = "选手1邮箱错误")
    @ApiModelProperty(value = "选手1邮箱，非空", example = "1840347063@qq.com")
    private String member1Email;

    @Email(message = "选手2邮箱错误")
    @ApiModelProperty(value = "选手2邮箱，非空", example = "702072637@qq.com")
    private String member2Email;

    @Email(message = "选手3邮箱错误")
    @ApiModelProperty(value = "选手3邮箱，非空", example = "1187940117@qq.com")
    private String member3Email;

    @Email(message = "教练1邮箱错误")
    @ApiModelProperty(value = "主教练邮箱，非空", example = "2212721955@qq.com")
    private String coach1Email;

    @ApiModelProperty(value = "副教练邮箱，允许非空", example = "")
    private String coach2Email;

    @NotBlank
    @ApiModelProperty(value = "比赛id，非空", example = "xxxxx")
    private String competitionId;

    @NotNull
    @ApiModelProperty(value = "参赛类型，非空", example = "1外卡，2打星，3正式队伍")
    private Integer type;

    @NotNull
    @ApiModelProperty(value = "是否需要电子版证书，非空", example = "1不需要，2需要")
    private Integer needTeamCertificate;
}
