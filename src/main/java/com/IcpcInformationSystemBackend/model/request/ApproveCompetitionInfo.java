package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "审批比赛和比赛负责人需提供的信息")
public class ApproveCompetitionInfo {
    @NotBlank(message = "比赛id错误")
    @ApiModelProperty(value = "比赛id，非空", example = "xxxxx")
    private String competitionId;

    @NotNull(message = "审批结果不能为空")
    @ApiModelProperty(value = "审批结果，非空", example = "2批准，3驳回")
    private Integer approveResult;

    @NotBlank(message = "审批理由不能为空")
    @ApiModelProperty(value = "审批理由，非空", example = "资料审核合格")
    private String approveReason;
}
