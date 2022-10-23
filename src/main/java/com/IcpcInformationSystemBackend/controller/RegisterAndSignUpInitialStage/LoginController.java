package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;

import com.IcpcInformationSystemBackend.model.request.LoginUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.LoginService;
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
@RequestMapping("/login")
@Api(tags = "登陆接口类")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/loginUser")
    @ApiOperation(value = "使用邮箱+密码+身份id，登陆当前账号")
    public Result loginUser(@ApiParam(name = "用户登录提供的信息", required = true) @Validated @RequestBody LoginUserInfo loginUserInfo) {
        return loginService.loginUser(loginUserInfo);
    }

    @GetMapping("/forgetUser")
    @ApiOperation(value = "使用邮箱以及验证码，来找到密码")
    public Result forgetUser(String email, String emailCode) {
        return loginService.forgetUser(email, emailCode);
    }
}
