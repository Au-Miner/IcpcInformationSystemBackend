package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;


import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.FileService;
import com.IcpcInformationSystemBackend.tools.EmailTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
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

    @Resource
    private EmailTool emailTool;

    @PostMapping("uploadSchoolImg")
    @ApiOperation(value = "上传学校校徽", notes = "仅能上传jpg/png格式图片，并返回图片地址")
    public Result uploadSchoolImg(@RequestBody MultipartFile file, String verificationCode, HttpServletRequest request) {
//        if (!emailTool.verifyVerificationCode(verificationCode, request))
//            return ResultTool.error(EmAllException.VERIFICATION_CODE_ERROR);
        return fileService.uploadSchoolImg(file);
    }

    @GetMapping("downloadLocalFile")
    @ApiOperation(value = "（仅供测试使用）下载本地文件")
    public void downloadLocalFile(HttpServletRequest request, HttpServletResponse response, String filePath) {
        fileService.downloadLocalFile(request, response, filePath);
    }

    @GetMapping("downloadRemoteFile")
    @ApiOperation(value = "（仅供测试使用）下载远程文件")
    public void downloadRemoteFile(HttpServletRequest request, HttpServletResponse response, String filePath) {
        fileService.downloadRemoteFile(request, response, filePath);
    }

    @GetMapping("downloadSchoolImg")
    @ApiOperation(value = "下载校徽")
    public void downloadSchoolImg(HttpServletRequest request, HttpServletResponse response, String filePath) {
        fileService.downloadRemoteFile(request, response, filePath);
    }
}
