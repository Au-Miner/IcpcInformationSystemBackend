package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;

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

    @PostMapping("/signUp4Competition")
    @ApiOperation(value = "选手通过提交队伍信息来报名比赛")
    public Result signUp4Competition(@ApiParam(name = "报名比赛创建队伍需要提供的信息", required = true) @Validated @RequestBody RegisterTeamInfo registerTeamInfo) {
        return teamService.studentSignUp4Competition(registerTeamInfo, true);
    }

    @GetMapping("/getOwnTeamInfo")
    @ApiOperation(value = "选手查看自己的队伍报名信息")
    public Result getOwnTeamInfo(String competitionId) {
        return teamService.getOwnTeamInfo(competitionId);
    }

    @PostMapping("/reSignUp4Competition")
    @ApiOperation(value = "选手通过提交队伍信息来重新报名比赛（选手修改队伍信息并重新提交审核）")
    public Result reSignUp4Competition(@ApiParam(name = "重新报名比赛创建队伍需要提供的信息", required = true) @Validated @RequestBody RegisterTeamInfo registerTeamInfo) {
        Result result = teamService.deleteTeamInfo(registerTeamInfo.getCompetitionId(), registerTeamInfo.getTeamId());
        if (result.getCode() != 200)
            return result;
        return teamService.studentSignUp4Competition(registerTeamInfo, false);
    }

    @GetMapping("/deleteTeamInfo")
    @ApiOperation(value = "选手删除自己队伍信息")
    public Result deleteTeamInfo(String competitionId, String teamId) {
        return teamService.deleteTeamInfo(competitionId, teamId);
    }
}
