package com.IcpcInformationSystemBackend.controller.CompetitionOverStage;

import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.request.PersonalCompetitionTeamScoreInfo;
import com.IcpcInformationSystemBackend.model.request.TeamCompetitionTeamScoreInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.TeamScoreInfoResponse;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.service.TeamService;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/competitionOver/competitionCertificateSearch")
@Api(tags = "比赛证书查询接口类（任何人可用）")
public class CompetitionCertificateSearchController {

    @Resource
    private FileService fileService;

    @Resource
    private TeamService teamService;

    @PostMapping("judgeTeamCompetitionCertificate")
    @ApiOperation(value = "判断团队比赛证书真伪")
    public Result judgeTeamCompetitionCertificate(@ApiParam(name = "更新当前比赛所有队伍成绩时需要提供的信息", required = true) @Validated @RequestBody TeamCompetitionTeamScoreInfo teamScoreInfo1) {
        TeamScoreInfoResponse teamScoreInfoResponse1 = teamService.getCompetitionCertificateInfo2(teamScoreInfo1.getCompetitionId(), teamScoreInfo1.getTeamId());
        if (teamScoreInfoResponse1 == null)
            return ResultTool.error(EmAllException.COMPETITION_CERTIFICATE_FAKE);
        TeamCompetitionTeamScoreInfo teamScoreInfo2 = new TeamCompetitionTeamScoreInfo();
        BeanUtils.copyProperties(teamScoreInfoResponse1, teamScoreInfo2);
        // log.info(teamScoreInfo1.toString());
        // log.info(teamScoreInfo2.toString());
        if (Objects.equals(teamScoreInfo1.toString(), teamScoreInfo2.toString()))
            return ResultTool.success();
        return ResultTool.error(EmAllException.COMPETITION_CERTIFICATE_FAKE);
    }

    @PostMapping("judgePersonalCompetitionCertificate")
    @ApiOperation(value = "判断个人比赛证书真伪")
    public Result judgePersonalCompetitionCertificate(@ApiParam(name = "更新当前比赛所有队伍成绩时需要提供的信息", required = true) @Validated @RequestBody PersonalCompetitionTeamScoreInfo teamScoreInfo1) {
        TeamScoreInfoResponse teamScoreInfoResponse1 = teamService.getCompetitionCertificateInfo2(teamScoreInfo1.getCompetitionId(), teamScoreInfo1.getTeamId());
        if (teamScoreInfoResponse1 == null)
            return ResultTool.error(EmAllException.COMPETITION_CERTIFICATE_FAKE);
        PersonalCompetitionTeamScoreInfo teamScoreInfo2 = new PersonalCompetitionTeamScoreInfo();
        BeanUtils.copyProperties(teamScoreInfoResponse1, teamScoreInfo2);
        // log.info(teamScoreInfo1.toString());
        // log.info(teamScoreInfo2.toString());
        if (Objects.equals(teamScoreInfo1.toString(), teamScoreInfo2.toString()))
            return ResultTool.success();
        return ResultTool.error(EmAllException.COMPETITION_CERTIFICATE_FAKE);
    }

    //生成二维码并将其存放于本地目录
    @GetMapping("generateQRCodeToFile")
    @ApiOperation(value = "（该接口暂时用不到了）获取字符串并生成二维码，将二维码存在本地")
    public String generateQRCodeToFile(String content) {
        return fileService.generateQRCodeToFile(content);
    }

    //生成二维码并将其返回给前端调用者
    @GetMapping("generateQRCodeToResponse")
    @ApiOperation(value = "（该接口暂时用不到了）获取字符串并生成二维码，将二维码返回给前端")
    public void generateQRCodeToResponse(HttpServletResponse servletResponse, String content) {
        fileService.generateQRCodeToResponse(servletResponse, content);
    }
}
