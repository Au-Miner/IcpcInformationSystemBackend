package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;

import com.IcpcInformationSystemBackend.model.request.ApproveTeamInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveUserInfo;
import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.ApproveService;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.EmailService;
import com.IcpcInformationSystemBackend.service.TeamService;
import com.IcpcInformationSystemBackend.tools.ResultTool;
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
@RequestMapping("/coach")
@Api(tags = "教练接口类")
public class CoachController {
    @Resource
    private ApproveService approveService;

    @Resource
    private EmailService emailService;

    @Resource
    private CompetitionService competitionService;

    @Resource
    private TeamService teamService;

    /*注册阶段*/
    @GetMapping("/getStudentRegitsterInfo")
    @ApiOperation(value = "教练或学校负责人在审核学生时在此接口获取选手所有相关信息")
    public Result getStudentRegitsterInfo() {
        return approveService.getStudentRegitsterInfo();
    }

    @PostMapping("/approveStudentRegister")
    @ApiOperation(value = "教练或学校负责人审批选手注册账号")
    public Result approveStudentRegister(@ApiParam(name = "审批选手时需要提供的信息", required = true) @Validated @RequestBody ApproveUserInfo approveUserInfo) {
        Result res1 = approveService.approveStudentRegister(approveUserInfo);
        if (res1.getCode() != 200)
            return res1;
        Result res2 = emailService.sendEmailMessage(approveUserInfo.getUserEmail(), approveUserInfo.getApproveReason());
        if (res2.getCode() != 200)
            return res2;
        return ResultTool.success();
    }

    /*比赛报名阶段*/
    @GetMapping("/getTeamInfoByCompetitionId")
    @ApiOperation(value = "教练或学校负责人根据比赛id获取当前学校所有自己带队队伍信息（方便后续进行审批）")
    public Result getTeamInfoByCompetitionId(String competitionId) {
        return approveService.coachGetTeamInfoByCompetitionId(competitionId);
    }

    @GetMapping("/getStudentInfo")
    @ApiOperation(value = "教练或学校负责人获取本校所有学生信息，在创建队伍的时候方便快捷选择学生")
    public Result getStudentInfo() {
        return approveService.getStudentInfo();
    }

    @PostMapping("/approveTeamInfoByTeamKey")
    @ApiOperation(value = "教练或学校负责人审核队伍报名信息")
    public Result approveTeamInfoByTeamKey(@ApiParam(name = "审核队伍时需要提供的信息", required = true) @Validated @RequestBody ApproveTeamInfo approveTeamInfo) {
        return approveService.coachApproveTeamInfoByTeamKey(approveTeamInfo);
    }

    @GetMapping("/deleteTeamInfo")
    @ApiOperation(value = "教练或学校负责人删除自己带队队伍信息")
    public Result deleteTeamInfo(String competitionId, String teamId) {
        return teamService.deleteTeamInfo(competitionId, teamId);
    }

    @PostMapping("/signUp4Competition")
    @ApiOperation(value = "教练或学校负责人通过提交队伍信息来报名比赛")
    public Result signUp4Competition(@ApiParam(name = "报名比赛创建队伍需要提供的信息", required = true) @Validated @RequestBody RegisterTeamInfo registerTeamInfo) {
        return teamService.coachSignUp4Competition(registerTeamInfo, true);
    }

    @PostMapping("/reSignUp4Competition")
    @ApiOperation(value = "教练或学校负责人通过提交队伍信息来重新报名比赛（教练修改队伍信息并重新提交审核）")
    public Result reSignUp4Competition(@ApiParam(name = "重新报名比赛创建队伍需要提供的信息", required = true) @Validated @RequestBody RegisterTeamInfo registerTeamInfo) {
        Result result = teamService.deleteTeamInfo(registerTeamInfo.getCompetitionId(), registerTeamInfo.getTeamId());
        if (result.getCode() != 200)
            return result;
        return teamService.coachSignUp4Competition(registerTeamInfo, false);
    }
}
