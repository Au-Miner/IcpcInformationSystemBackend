package com.IcpcInformationSystemBackend.controller.SignUpOverStage;

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
@RequestMapping("/signUpOver/position")
@Api(tags = "场地接口类")
public class PositionController {
    @Resource
    private PositionService positionService;

    @PostMapping("/addPosition")
    @ApiOperation(value = "比赛负责人添加比赛场地（包含每个比赛场地能容纳人数）")
    public Result addPosition(@ApiParam(name = "添加比赛场地时需要提供的信息", required = true) @Validated @RequestBody PositionInfo positionInfo) {
        return positionService.addPosition(positionInfo);
    }

    @GetMapping("/getPositionInfoByCompetitionId")
    @ApiOperation(value = "比赛负责人根据比赛id获取所有场地信息")
    public Result getPositionInfoByCompetitionId(String competitionId) {
        return positionService.getPositionInfoByCompetitionId(competitionId);
    }

    @PostMapping("/modifyPosition")
    @ApiOperation(value = "比赛负责人修改比赛场地信息")
    public Result modifyPosition(@ApiParam(name = "添加比赛场地时需要提供的信息", required = true) @Validated @RequestBody PositionInfo positionInfo) {
        return positionService.modifyPosition(positionInfo);
    }

    @GetMapping("/deletePosition")
    @ApiOperation(value = "比赛负责人根据场地id删除场地信息")
    public Result deletePosition(String positionId) {
        return positionService.deletePosition(positionId);
    }

    @GetMapping("/assignPositions")
    @ApiOperation(value = "比赛管理员随机分配所有队伍的比赛位置")
    public Result assignPositions(String competitionId) {
        return positionService.assignPositions(competitionId);
    }

    @GetMapping("/exchangePositions")
    @ApiOperation(value = "比赛管理员交换两个队伍的比赛位置")
    public Result exchangePositions(String competitionId, String teamId1, String teamId2) {
        return positionService.exchangePositions(competitionId, teamId1, teamId2);
    }
}
