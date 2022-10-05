package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.FileTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/file")
@Api(tags = "文件上传下载类")
public class FileController {
    @Resource
    private FileTool fileTool;

    @PostMapping("uploadImg")
    @ApiOperation(value = "上传图片", notes = "仅能上传jpg/png格式图片，并返回图片地址")
    public Result uploadImg(@RequestBody MultipartFile file) {
        try {
            return ResultTool.success(fileTool.uploadImg(file));
        } catch (AllException e) {
            return ResultTool.error(e.getErrCode(), e.getMsg());
        }
    }
}
