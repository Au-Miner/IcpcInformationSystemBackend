package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/login")
@Api(tags = "登陆接口类")
public class LoginController {
    @Resource
    LoginService loginService;

    @GetMapping("/loginUser")
    @ApiOperation(value = "使用id/邮箱+密码，登陆当前账号")
    public Result loginUser(String key, String password) {
        return loginService.loginUser(key, password);
    }

    @GetMapping("/forgetUser")
    @ApiOperation(value = "使用邮箱以及验证码，来找到密码")
    public Result forgetUser(String email, String emailCode) {
        return loginService.forgetUser(email, emailCode);
    }
}
