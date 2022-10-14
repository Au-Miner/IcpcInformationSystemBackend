package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "修改比赛信息时需要提供的信息")
public class CompetitionModifyInfo {
    @NotBlank
    @ApiModelProperty(value = "比赛id号，如果为空表示创建比赛，如果非空表示比赛信息修改", example = "xxxxx")
    private String competitionId;

    @NotBlank(message="举办场地不能为空")
    @ApiModelProperty(value = "举办场地，非空", example = "上海市宝山区上海大学宝山校区")
    private String heldPosition;

    @NotBlank(message="比赛介绍不能为空")
    @ApiModelProperty(value = "比赛介绍，非空", example = "此次比赛由上海大学举办，是一场重大赛事")
    private String competitionIntroduction;
}
