package com.IcpcInformationSystemBackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "新增比赛场地需要提供的信息PositionInfo")
public class PositionInfo {
    @NotBlank(message="比赛id不能为空")
    @ApiModelProperty(value = "比赛id，非空", example = "xxxxx")
    private String competitionId;

    @NotBlank(message="场地名不能为空")
    @ApiModelProperty(value = "场地名，非空",  example = "A区")
    private String positionName;

    @NotNull(message="场地容量不能为空")
    @ApiModelProperty(value = "场地容量，非空", example = "例如100")
    private Integer capacity;
}
