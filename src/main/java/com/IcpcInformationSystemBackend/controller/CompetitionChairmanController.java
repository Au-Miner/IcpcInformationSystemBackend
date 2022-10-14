package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.request.CompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.CompetitionModifyInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/competitionChairman")
@Api(tags = "比赛负责人接口类")
public class CompetitionChairmanController {
    @Resource
    private CompetitionService competitionService;

    @PostMapping("/buildCompetition")
    @ApiOperation(value = "比赛负责人创建比赛")
    public Result buildCompetition(@ApiParam(name = "创建比赛需提供的信息", required = true) @Validated @RequestBody CompetitionInfo competitionInfo) {
        return competitionService.buildCompetition(competitionInfo);
    }

    @PostMapping("/rebuildCompetition")
    @ApiOperation(value = "比赛被驳回后，比赛负责人重新申请创建比赛")
    public Result rebuildCompetition(@ApiParam(name = "创建比赛需提供的信息", required = true) @Validated @RequestBody CompetitionInfo competitionInfo) {
        return competitionService.rebuildCompetition(competitionInfo);
    }

    @GetMapping("/getOwnCompetitionInfo")
    @ApiOperation(value = "比赛负责人查看自己创建比赛的信息，注意这里返回值有关个人信息内容都为null属于正常返回")
    public Result getOwnCompetitionInfo() {
        return competitionService.getOwnCompetitionInfo();
    }

    @PostMapping("/modifyCompetition")
    @ApiOperation(value = "比赛负责人可以申请修改已经被批准成功的比赛信息")
    public Result modifyCompetition(@ApiParam(name = "修改比赛需提供的信息", required = true) @Validated @RequestBody CompetitionModifyInfo competitionModifyInfo) {
        return competitionService.modifyCompetition(competitionModifyInfo);
    }
}
