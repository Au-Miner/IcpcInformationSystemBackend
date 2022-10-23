package com.IcpcInformationSystemBackend.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "创建比赛时需要提供的信息RegisterCompetitionInfo")
public class RegisterCompetitionInfo {
    @ApiModelProperty(value = "比赛id号，如果为空表示创建比赛，如果非空表示重新创建比赛/比赛信息修改", example = "xxxxx")
    private String competitionId;

    @NotBlank(message="比赛中文名不能为空")
    @ApiModelProperty(value = "比赛中文名，非空", example = "ICPC上海站")
    private String competitionChiName;

    @NotBlank(message="比赛英文名不能为空")
    @ApiModelProperty(value = "比赛英文名，非空", example = "ICPC shanghai")
    private String competitionEngName;

    @NotBlank(message="举办场地不能为空")
    @ApiModelProperty(value = "举办场地，非空", example = "上海市宝山区上海大学宝山校区")
    private String heldPosition;

    @NotNull(message="报名开始时间不能为空")
    @ApiModelProperty(value = "报名开始时间，非空", example = "2022-10-12 09:01:16")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registrationStartTime;

    @NotNull(message="报名截止时间不能为空")
    @ApiModelProperty(value = "报名截止时间，非空", example = "2022-10-12 09:01:16")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registrationEndTime;

    @NotNull(message="缴费开始时间不能为空")
    @ApiModelProperty(value = "缴费开始时间，非空", example = "2022-10-12 09:01:16")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date payStartTime;

    @NotNull(message="缴费截止时间不能为空")
    @ApiModelProperty(value = "缴费截止时间，非空", example = "2022-10-12 09:01:16")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date payEndTime;

    @NotNull(message="比赛开始时间不能为空")
    @ApiModelProperty(value = "比赛开始时间，非空", example = "2022-10-12 09:01:16")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date competitionStartTime;

    @NotBlank(message="比赛持续时间不能为空")
    @ApiModelProperty(value = "比赛持续时间，非空", example = "05:00")
    private String duration;

    @NotBlank(message="比赛介绍不能为空")
    @ApiModelProperty(value = "比赛介绍，非空", example = "此次比赛由上海大学举办，是一场重大赛事")
    private String competitionIntroduction;
}
