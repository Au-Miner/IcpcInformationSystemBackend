package com.IcpcInformationSystemBackend.controller.CompetitionOverStage;


import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.service.TeamService;
import com.IcpcInformationSystemBackend.tools.FileTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import com.itextpdf.text.DocumentException;
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
import java.io.IOException;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/competitionOver/competitionCertificate")
@Api(tags = "比赛证书查询接口类（登录可用）")
public class CompetitionCertificateController {

    @Resource
    private TeamService teamService;

    @Resource
    private FileService fileService;

    @GetMapping("/getCompetitionCertificateInfo")
    @ApiOperation(value = "（该接口暂时用不到了）队伍成员或教练获取当前队伍的获奖证书信息")
    public Result getCompetitionCertificateInfo(String competitionId, String teamId) {
        return teamService.getCompetitionCertificateInfo(competitionId, teamId);
    }

    @GetMapping("/downloadCompetitionCertificate")
    @ApiOperation(value = "队伍成员或教练获取当前队伍的获奖证书pdf")
    public void downloadCompetitionCertificate(HttpServletRequest request, HttpServletResponse response, String competitionId, String teamId) {
        fileService.downloadCompetitionCertificate(request, response, competitionId, teamId);
    }
}
