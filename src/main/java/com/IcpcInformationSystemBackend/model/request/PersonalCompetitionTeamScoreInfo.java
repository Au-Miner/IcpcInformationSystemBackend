package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "判断比赛证书真伪时需要提供的信息PersonalCompetitionTeamScoreInfo")
public class PersonalCompetitionTeamScoreInfo {
    @NotBlank(message = "队伍id错误")
    @ApiModelProperty(value = "队伍id，非空", example = "xxxxx")
    private String teamId;

    @NotBlank(message = "比赛id错误")
    @ApiModelProperty(value = "比赛id，非空", example = "xxxxx")
    private String competitionId;

    @NotBlank(message = "比赛中文名错误")
    @ApiModelProperty(value = "比赛中文名，非空", example = "xxxxx")
    private String competitionChiName;

    @NotBlank(message = "比赛中文时间错误")
    @ApiModelProperty(value = "比赛中文时间，非空", example = "xxxxx")
    private String competitionChiTime;

    @NotBlank(message = "中文校名错误")
    @ApiModelProperty(value = "中文校名，非空", example = "xxxxx")
    private String chiSchoolName;

    @NotBlank(message = "选手1中文名错误")
    @ApiModelProperty(value = "选手1中文名，非空", example = "xxxxx")
    private String member1chiName;

    @NotBlank(message = "奖项中文名错误")
    @ApiModelProperty(value = "奖项中文名，非空", example = "xxxxx")
    private String chiMedal;
}