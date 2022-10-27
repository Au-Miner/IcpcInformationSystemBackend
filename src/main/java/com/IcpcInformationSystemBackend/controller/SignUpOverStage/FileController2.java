package com.IcpcInformationSystemBackend.controller.SignUpOverStage;

import com.IcpcInformationSystemBackend.model.response.CompetitionAdmissionTicketResponse;
import com.IcpcInformationSystemBackend.model.response.CompetitionEntryListResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/signUpOver/file2")
@Api(tags = "比赛报名截止阶段文件接口类（登录用户可使用）")
public class FileController2 {
    @Resource
    private CompetitionService competitionService;

    @Resource
    private TeamService teamService;

    @Resource
    private FileService fileService;

    @GetMapping("/getCompetitionEntryList")
    @ApiOperation(value = "（该接口暂时用不到了）任何登录用户可以获取当前比赛的总参赛名单")
    public Result getCompetitionEntryList(String competitionId) {
        return competitionService.getCompetitionEntryList(competitionId);
    }

    @GetMapping(value = "/downloadCompetitionEntryList")
    @ApiOperation(value = "任何登录用户可以获取当前比赛的总参赛名单（excel返回）")
    public void downloadCompetitionEntryList(HttpServletResponse response, HttpServletRequest request, String competitionId) {
        ArrayList<CompetitionEntryListResponse> competitionEntryList2 = competitionService.getCompetitionEntryList2(competitionId);
        ArrayList<String> colHead = new ArrayList<>();
        colHead.add("队伍中文名");
        colHead.add("队伍英文名");
        colHead.add("学校id");
        colHead.add("选手1姓名");
        colHead.add("选手2姓名");
        colHead.add("选手3姓名");
        colHead.add("教练1姓名");
        colHead.add("教练2姓名");
        colHead.add("队伍身份");
        fileService.downloadCompetitionEntryList(response, colHead, competitionEntryList2);
    }

    @GetMapping("/getCompetitionAdmissionTicket")
    @ApiOperation(value = "（该接口暂时用不到了）参赛选手可以查看当前队伍的准考证（必须是本队参赛选手）")
    public Result getCompetitionAdmissionTicket(String competitionId, String teamId) {
        return teamService.getCompetitionAdmissionTicket(competitionId, teamId);
    }

    @GetMapping("/downCompetitionAdmissionTicket")
    @ApiOperation(value = "参赛选手可以下载当前队伍的准考证（pdf返回）（必须是本队参赛选手）")
    public void downCompetitionAdmissionTicket(HttpServletResponse response, HttpServletRequest request, String competitionId, String teamId) {
        CompetitionAdmissionTicketResponse competitionAdmissionTicketResponse = teamService.getCompetitionAdmissionTicket2(competitionId, teamId);
        if (competitionAdmissionTicketResponse == null) {
            log.info("为空！！！！");
            return;
        }
        fileService.downCompetitionAdmissionTicket(request, response, competitionAdmissionTicketResponse);
    }
}
