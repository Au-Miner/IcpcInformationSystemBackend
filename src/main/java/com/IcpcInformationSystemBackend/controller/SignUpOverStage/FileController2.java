package com.IcpcInformationSystemBackend.controller.SignUpOverStage;

import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/signUpOver/file2")
@Api(tags = "比赛报名截止阶段文件接口类（登录用户可使用）")
public class FileController2 {
    @Resource
    private CompetitionService competitionService;


    @GetMapping("/getCompetitionEntryList")
    @ApiOperation(value = "登录用户可以获取当前比赛的总参赛名单（因为是前端负责生成pdf/excel，所以无需后端返回pdf/excel了）")
    public Result getCompetitionEntryList(String competitionId) {
        return competitionService.getCompetitionEntryList(competitionId);
    }

    @GetMapping("/getCompetitionAdmissionTicket")
    @ApiOperation(value = "参赛选手可以查看当前队伍的准考证（因为是前端负责生成pdf/excel，所以无需后端返回pdf/excel了）")
    public Result getCompetitionAdmissionTicket(String competitionId, String teamId) {
        return competitionService.getCompetitionAdmissionTicket(competitionId, teamId);
    }
}
