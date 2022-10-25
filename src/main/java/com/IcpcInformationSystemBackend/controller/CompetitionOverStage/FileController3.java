package com.IcpcInformationSystemBackend.controller.CompetitionOverStage;

import com.IcpcInformationSystemBackend.tools.FileTool;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/competitionOver/file3")
@Api(tags = "比赛结束阶段文件接口类（仅比赛负责人可使用）")
public class FileController3 {

    @Resource
    private FileTool fileTool;



    // @PostMapping("uploadTeamPhoto")
    // @ApiOperation(value = "上传队伍参赛图片", notes = "仅能上传jpg/png格式图片，并返回图片地址")
    // public Result uploadImg(@RequestBody MultipartFile file) {
    //     try {
    //         return ResultTool.success(fileTool.uploadImg(file));
    //     } catch (AllException e) {
    //         return ResultTool.error(e.getErrCode(), e.getMsg());
    //     }
    // }
}
