package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "更新当前比赛所有队伍成绩时需要提供的信息UpdateTeamScoresInfo")
public class UpdateTeamScoresInfo {
    @NotBlank(message = "比赛id错误")
    @ApiModelProperty(value = "比赛id，非空", example = "xxxxx")
    private String competitionId;

    @NotBlank(message = "队伍成绩表地址错误")
    @ApiModelProperty(value = "队伍成绩表地址，非空", example = "xxxxx")
    private String teamScoresAddress;
}
