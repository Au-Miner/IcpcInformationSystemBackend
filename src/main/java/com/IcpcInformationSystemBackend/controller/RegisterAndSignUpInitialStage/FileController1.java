package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;


import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/file1")
@Api(tags = "文件接口类")
public class FileController1 {
    @Resource
    private FileService fileService;

    @PostMapping("uploadSchoolImg")
    @ApiOperation(value = "上传学校校徽", notes = "仅能上传jpg/png格式图片，并返回图片地址")
    public Result uploadSchoolImg(@RequestBody MultipartFile file) {
        return fileService.uploadSchoolImg(file);
    }

    @GetMapping("downloadFile")
    @ApiOperation(value = "下载文件，仅供测试使用")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath) {
        fileService.downloadFile(request, response, filePath);
    }
}
