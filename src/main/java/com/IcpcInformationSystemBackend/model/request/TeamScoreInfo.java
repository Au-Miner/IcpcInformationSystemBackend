package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "判断比赛证书真伪时需要提供的信息TeamScoreInfo")
public class TeamScoreInfo {
    @NotBlank(message = "队伍id错误")
    @ApiModelProperty(value = "队伍id，非空", example = "xxxxx")
    private String teamId;

    @NotBlank(message = "队伍中文名错误")
    @ApiModelProperty(value = "队伍中文名，非空", example = "xxxxx")
    private String chiTeamName;

    @NotBlank(message = "队伍英文名错误")
    @ApiModelProperty(value = "队伍英文名，非空", example = "xxxxx")
    private String engTeamName;;

    @NotBlank(message = "比赛id错误")
    @ApiModelProperty(value = "比赛id，非空", example = "xxxxx")
    private String competitionId;

    @NotBlank(message = "比赛中文名错误")
    @ApiModelProperty(value = "比赛中文名，非空", example = "xxxxx")
    private String competitionChiName;

    @NotBlank(message = "比赛英文名错误")
    @ApiModelProperty(value = "比赛英文名，非空", example = "xxxxx")
    private String competitionEngName;

    @NotBlank(message = "比赛中文时间错误")
    @ApiModelProperty(value = "比赛中文时间，非空", example = "xxxxx")
    private String competitionChiTime;

    @NotBlank(message = "比赛英文时间错误")
    @ApiModelProperty(value = "比赛英文时间，非空", example = "xxxxx")
    private String competitionEngTime;

    @NotBlank(message = "学校校徽错误")
    @ApiModelProperty(value = "学校校徽，非空", example = "xxxxx")
    private String schoolImg;

    @NotBlank(message = "中文校名错误")
    @ApiModelProperty(value = "中文校名，非空", example = "xxxxx")
    private String chiSchoolName;

    @NotBlank(message = "英文校名错误")
    @ApiModelProperty(value = "英文校名，非空", example = "xxxxx")
    private String engSchoolName;

    @NotBlank(message = "选手1中文名错误")
    @ApiModelProperty(value = "选手1中文名，非空", example = "xxxxx")
    private String member1chiName;

    @NotBlank(message = "选手1英文名错误")
    @ApiModelProperty(value = "选手1英文名，非空", example = "xxxxx")
    private String member1engName;

    @NotBlank(message = "选手2中文名错误")
    @ApiModelProperty(value = "选手2中文名，非空", example = "xxxxx")
    private String member2chiName;

    @NotBlank(message = "选手2英文名错误")
    @ApiModelProperty(value = "选手2英文名，非空", example = "xxxxx")
    private String member2engName;

    @NotBlank(message = "选手3中文名错误")
    @ApiModelProperty(value = "选手3中文名，非空", example = "xxxxx")
    private String member3chiName;

    @NotBlank(message = "选手3英文名错误")
    @ApiModelProperty(value = "选手3英文名，非空", example = "xxxxx")
    private String member3engName;

    @NotBlank(message = "教练1中文名错误")
    @ApiModelProperty(value = "教练1中文名，非空", example = "xxxxx")
    private String coach1chiName;

    @NotBlank(message = "教练1英文名错误")
    @ApiModelProperty(value = "教练1英文名，非空", example = "xxxxx")
    private String coach1engName;

    @ApiModelProperty(value = "教练2中文名", example = "xxxxx")
    private String coach2chiName;

    @ApiModelProperty(value = "教练2英文名", example = "xxxxx")
    private String coach2engName;

    @NotNull(message = "比赛排名错误")
    @ApiModelProperty(value = "比赛排名，非空", example = "xxxxx")
    private Integer rnk;

    @NotBlank(message = "奖项中文名错误")
    @ApiModelProperty(value = "奖项中文名，非空", example = "xxxxx")
    private String chiMedal;

    @NotBlank(message = "奖项英文名错误")
    @ApiModelProperty(value = "奖项英文名，非空", example = "xxxxx")
    private String engMedal;
}
