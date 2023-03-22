package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;

import com.IcpcInformationSystemBackend.model.request.ApproveTeamInfo;
import com.IcpcInformationSystemBackend.model.request.PositionInfo;
import com.IcpcInformationSystemBackend.model.request.RegisterCompetitionInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.ApproveService;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.PositionService;
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

    @Resource
    private ApproveService approveService;

    @Resource
    private PositionService positionService;

    @PostMapping("/buildCompetition")
    @ApiOperation(value = "比赛负责人创建比赛")
    public Result buildCompetition(@ApiParam(name = "创建比赛需提供的信息", required = true) @Validated @RequestBody RegisterCompetitionInfo registerCompetitionInfo) {
        return competitionService.buildCompetition(registerCompetitionInfo);
    }

    @PostMapping("/rebuildCompetition")
    @ApiOperation(value = "比赛被驳回后，比赛负责人重新申请创建比赛")
    public Result rebuildCompetition(@ApiParam(name = "创建比赛需提供的信息", required = true) @Validated @RequestBody RegisterCompetitionInfo registerCompetitionInfo) {
        return competitionService.rebuildCompetition(registerCompetitionInfo, 3);
    }

    @GetMapping("/getOwnCompetitionInfo")
    @ApiOperation(value = "比赛负责人查看自己创建比赛的信息，注意这里返回值有关个人信息内容都为null属于正常返回")
    public Result getOwnCompetitionInfo() {
        return competitionService.getOwnCompetitionInfo();
    }

    @PostMapping("/modifyCompetition")
    @ApiOperation(value = "比赛负责人可以申请修改已经被批准成功的比赛信息")
    public Result modifyCompetition(@ApiParam(name = "修改比赛需提供的信息", required = true) @Validated @RequestBody RegisterCompetitionInfo registerCompetitionInfo) {
        return competitionService.rebuildCompetition(registerCompetitionInfo, 2);
    }

    @GetMapping("/getTeamInfoByCompetitionId")
    @ApiOperation(value = "比赛负责人根据比赛id获取所有队伍信息")
    public Result getTeamInfoByCompetitionId(String competitionId) {
        return approveService.competitionChairmanGetTeamInfoByCompetitionId(competitionId);
    }

    @PostMapping("/approveTeamInfoByTeamKey")
    @ApiOperation(value = "比赛负责人审核队伍报名信息")
    public Result approveTeamInfoByTeamKey(@ApiParam(name = "审核队伍时需要提供的信息", required = true) @Validated @RequestBody ApproveTeamInfo approveTeamInfo) {
        return approveService.competitionChairmanApproveTeamInfoByTeamKey(approveTeamInfo);
    }

    @GetMapping("/deleteCompetition")
    @ApiOperation(value = "比赛负责人删除比赛")
    public Result deleteCompetition(String competitionId) {
        return competitionService.deleteCompetition(competitionId);
    }
}
