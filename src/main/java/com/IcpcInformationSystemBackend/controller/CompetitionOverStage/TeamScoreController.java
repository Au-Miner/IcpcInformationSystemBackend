package com.IcpcInformationSystemBackend.controller.CompetitionOverStage;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.request.UpdateTeamScoresInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.tools.FileTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
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
import java.io.IOException;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/competitionOver/teamScore")
@Api(tags = "比赛结束阶段文件接口（仅比赛负责人可使用）")
public class TeamScoreController {
    @Value("${upload.teamScoresDemo}")
    private String teamScoresDemoAddress;

    @Resource
    private FileTool fileTool;

    @Value("${upload.teamScoresPath}")
    private String directoryNeed;

    @Resource
    private CompetitionService competitionService;

    @GetMapping("downloadTeamScoresDemo")
    @ApiOperation(value = "下载队伍成绩表模板")
    public Result downloadTeamScoresDemo(HttpServletRequest request, HttpServletResponse response) {
        try {
            fileTool.downloadFile(request, response, teamScoresDemoAddress);
        } catch (IOException | AllException e) {
            return ResultTool.error(EmAllException.DATABASE_ERR);
        }
        return ResultTool.success();
    }


    @PostMapping("uploadTeamScores")
    @ApiOperation(value = "上传比赛所有队伍成绩表，以excel形式上传，并返回文件地址", notes = "仅能上传.xls和.xlsx形式文件，并返回文件地址")
    public Result uploadTeamScores(@RequestBody MultipartFile file) {
        try {
            return ResultTool.success(fileTool.uploadExcel(file, directoryNeed));
        } catch (AllException e) {
            return ResultTool.error(e.getErrCode(), e.getMsg());
        }
    }

    @PostMapping("updateTeamScores")
    @ApiOperation(value = "上传/更新当前比赛所有队伍成绩")
    public Result updateTeamScores(@ApiParam(name = "更新当前比赛所有队伍成绩时需要提供的信息", required = true) @Validated @RequestBody UpdateTeamScoresInfo updateTeamScoresInfo) {
        Result result = competitionService.updateTeamScores(updateTeamScoresInfo);
        if (result.getCode() != 200)
            return result;
        try {
            fileTool.deleteFile(updateTeamScoresInfo.getTeamScoresAddress());
        } catch (AllException e) {
            return ResultTool.error(e.getErrCode(), e.getMsg());
        }
        return ResultTool.success();
    }
}
