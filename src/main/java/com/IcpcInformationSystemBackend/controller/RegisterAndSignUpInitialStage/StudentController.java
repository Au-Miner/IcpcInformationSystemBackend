package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;

import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.CompetitionInfoResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.TeamInfoResponse;
import com.IcpcInformationSystemBackend.service.ApproveService;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.TeamService;
import com.IcpcInformationSystemBackend.service.UserService;
import com.IcpcInformationSystemBackend.tools.CommonTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;


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

    @Resource
    private UserService userService;

    @PostMapping("/signUp4Competition")
    @ApiOperation(value = "选手通过提交队伍信息来报名团队赛！！")
    public Result signUp4Competition(@ApiParam(name = "报名比赛创建队伍需要提供的信息", required = true) @Validated @RequestBody RegisterTeamInfo registerTeamInfo) {
        return teamService.studentSignUp4Competition(registerTeamInfo, true);
    }

    @GetMapping("/signUp4PersonalCompetition")
    @ApiOperation(value = "选手通过提交队伍信息来报名个人赛！！")
    public Result signUp4PersonalCompetition(String competitionId, Integer type, Integer needTeamCertificate) {
        return teamService.studentSignUp4PersonalCompetition(competitionId, type, needTeamCertificate);
    }

    @GetMapping("/getOwnTeamInfo")
    @ApiOperation(value = "选手查看自己的队伍报名信息")
    public Result getOwnTeamInfo(String competitionId) {
        return teamService.getOwnTeamInfo(competitionId);
    }

    @PostMapping("/reSignUp4Competition")
    @ApiOperation(value = "选手通过提交队伍信息来重新报名团队赛！！（选手修改队伍信息并重新提交审核）")
    public Result reSignUp4Competition(@ApiParam(name = "重新报名比赛创建队伍需要提供的信息", required = true) @Validated @RequestBody RegisterTeamInfo registerTeamInfo) {
        Result res1 = competitionService.checkTeamCompetitionType(registerTeamInfo.getCompetitionId());
        if (res1.getCode() != 200)
            return res1;
        Result res2 = teamService.deleteTeamInfo(registerTeamInfo.getCompetitionId(), registerTeamInfo.getTeamId());
        if (res2.getCode() != 200)
            return res2;
        return teamService.studentSignUp4Competition(registerTeamInfo, false);
    }

    @GetMapping("/deleteTeamInfo")
    @ApiOperation(value = "选手删除自己队伍信息")
    public Result deleteTeamInfo(String competitionId, String teamId) {
        return teamService.deleteTeamInfo(competitionId, teamId);
    }

    @GetMapping("/getSelfUserInfo")
    @ApiOperation(value = "选手查看自己的个人信息")
    public Result getSelfUserInfo() {
        return userService.getSelfUserInfo();
    }

    @GetMapping("/getSelfCompetitionInfo")
    @ApiOperation(value = "选手获取自己参加所有比赛的信息")
    public Result getSelfCompetitionInfo() {
        return userService.getSelfCompetitionInfo();
    }

    @GetMapping("/getAllOwnTeamInfo")
    @ApiOperation(value = "选手获取所有自己参加的队伍信息")
    public Result getAllOwnTeamInfo() {
        Result res1 = userService.getSelfCompetitionInfo();
        if (res1.getCode() != 200)
            return res1;
        ArrayList<CompetitionInfoResponse> competitionInfoResponses = (ArrayList<CompetitionInfoResponse>) res1.getData();
        ArrayList<TeamInfoResponse> res = new ArrayList<>();
        for (CompetitionInfoResponse competitionInfoResponse : competitionInfoResponses) {
            // log.info("competitionInfoResponse:" + competitionInfoResponse.getCompetitionId());
            Result res2 = teamService.getOwnTeamInfo(competitionInfoResponse.getCompetitionId());
            if (res2.getCode() == 200) {
                TeamInfoResponse tmp = ((ArrayList<TeamInfoResponse>) res2.getData()).get(0);
                tmp.setCompetitionChiName(competitionInfoResponse.getCompetitionChiName());
                res.add(tmp);
            }
        }
        return ResultTool.success(res);
    }
}
