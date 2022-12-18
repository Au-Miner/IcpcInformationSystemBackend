package com.IcpcInformationSystemBackend.controller.CompetitionOverStage;

import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.request.UpdateTeamScoresInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.tools.EmailTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import com.ramostear.captcha.HappyCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/competitionOver/teamResultEnroll")
@Api(tags = "比赛结果登记接口（仅比赛负责人可使用）")
public class TeamResultEnrollController {
    @Value("${static.teamScoresDemo}")
    private String teamScoresDemoAddress;

    @Resource
    private FileService fileService;

    @Resource
    private CompetitionService competitionService;

    @Resource
    private EmailTool emailTool;

    @GetMapping("downloadTeamScoresDemo")
    @ApiOperation(value = "下载队伍成绩表模板")
    public void downloadTeamScoresDemo(HttpServletRequest request, HttpServletResponse response) {
        fileService.downloadStaticFile(request, response, teamScoresDemoAddress, "teamScoresDemo.xlsx");
    }

    @PostMapping("uploadTeamScores")
    @ApiOperation(value = "上传比赛所有队伍成绩表，以excel形式上传，并返回文件地址", notes = "仅能上传.xls和.xlsx形式文件，并返回文件地址")
    public Result uploadTeamScores(@RequestBody MultipartFile file, String verificationCode, HttpServletRequest request) {
        if (!emailTool.verifyVerificationCode(verificationCode, request))
            return ResultTool.error(EmAllException.VERIFICATION_CODE_ERROR);
        return fileService.uploadExcel(file);
    }

    @PostMapping("updateTeamScores")
    @ApiOperation(value = "上传/更新当前比赛所有队伍成绩")
    public Result updateTeamScores(@ApiParam(name = "更新当前比赛所有队伍成绩时需要提供的信息", required = true) @Validated @RequestBody UpdateTeamScoresInfo updateTeamScoresInfo) {
        Result result = competitionService.updateTeamScores(updateTeamScoresInfo);
        if (result.getCode() != 200)
            return result;
        return fileService.deleteFile(updateTeamScoresInfo.getTeamScoresAddress());
    }

    @PostMapping("uploadTeamPhoto")
    @ApiOperation(value = "上传参赛人员合影", notes = "仅能上传jpg/png格式图片，并返回图片地址")
    public Result uploadTeamPhoto(@RequestBody MultipartFile file, String competitionId, String teamId) {
        return fileService.uploadTeamPhoto(file, competitionId, teamId);
    }
}
