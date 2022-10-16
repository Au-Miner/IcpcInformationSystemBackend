package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.request.CompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.ApproveService;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.TeamService;
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
@RequestMapping("/student")
@Api(tags = "选手接口类")
public class StudentController {
    @Resource
    private ApproveService approveService;

    @Resource
    private CompetitionService competitionService;

    @Resource
    private TeamService teamService;

    @GetMapping("/getAcceptCompetitionInfo")
    @ApiOperation(value = "选手获取所有已批准通过比赛信息")
    public Result getAcceptCompetitionInfo() {
        return competitionService.getAllAcceptCompetitionInfo();
    }

    @PostMapping("/signUp4Competition")
    @ApiOperation(value = "选手通过提交队伍信息来报名比赛")
    public Result signUp4Competition(@ApiParam(name = "报名比赛创建队伍需要提供的信息", required = true) @Validated @RequestBody RegisterTeamInfo registerTeamInfo) {
        return teamService.signUp4Competition(registerTeamInfo);
    }
}
