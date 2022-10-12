package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.request.CompetitionInfo;
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
}
