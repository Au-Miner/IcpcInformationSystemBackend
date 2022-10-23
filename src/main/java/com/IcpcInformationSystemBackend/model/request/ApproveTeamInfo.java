package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "审批队伍需提供的信息ApproveTeamInfo")
public class ApproveTeamInfo {
    @NotBlank(message = "比赛id错误")
    @ApiModelProperty(value = "比赛id，非空", example = "xxxxx")
    private String competitionId;

    @NotBlank(message = "队伍id错误")
    @ApiModelProperty(value = "队伍id，非空", example = "xxxxx")
    private String teamId;

    @NotNull(message = "审批结果不能为空")
    @ApiModelProperty(value = "审批结果，非空", example = "2教练审核通过，3教练审核驳回，4比赛管理员审核通过，5比赛管理员审核驳回")
    private Integer approveResult;

    @NotBlank(message = "审批理由不能为空")
    @ApiModelProperty(value = "审批理由，非空", example = "资料审核合格")
    private String approveReason;
}
