package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.request.EmailMessageInfo;
import com.IcpcInformationSystemBackend.model.request.LoginUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/email")
@Api(tags = "邮箱接口类")
public class EmailController {
    @Resource
    private EmailService emailService;

    @GetMapping("/sendEmailCode")
    @ApiOperation(value = "输入邮箱地址，获取验证码")
    public Result sendEmailCode(String emailAddress) {
        return emailService.sendEmailCode(emailAddress);
    }

    @PostMapping("/sendEmailMessage")
    @ApiOperation(value = "向目标邮箱发送邮件")
    public Result sendEmailMessage(@ApiParam(name = "发送邮件需提供的信息", required = true) @Validated @RequestBody EmailMessageInfo emailMessageInfo) {
        return emailService.sendEmailMessage(emailMessageInfo.getEmail(), emailMessageInfo.getMessage());
    }
}
