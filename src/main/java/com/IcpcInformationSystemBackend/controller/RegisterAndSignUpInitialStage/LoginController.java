package com.IcpcInformationSystemBackend.controller.RegisterAndSignUpInitialStage;

import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.request.LoginUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.LoginService;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/login")
@Api(tags = "登陆接口类")
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private CompetitionService competitionService;

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

    @GetMapping("/getAcceptCompetitionInfo")
    @ApiOperation(value = "获取所有已批准通过比赛信息（放这里是因为这里不需要权限）")
    public Result getAcceptCompetitionInfo() {
        return competitionService.getAllAcceptCompetitionInfo();
    }

    @GetMapping("/generateVerificationCode")
    @ApiOperation(value = "生成验证码接口")
    public void generateVerificationCode(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request,response).type(CaptchaType.NUMBER).build().finish();
    }

    @PostMapping("/verifyVerificationCode")
    @ApiOperation(value = "验证验证码接口")
    public Result verifyVerificationCode(String code, HttpServletRequest request){
        if (HappyCaptcha.verification(request, code, true)) {
            HappyCaptcha.remove(request);
            return ResultTool.success();
        }
        return ResultTool.error(EmAllException.VERIFICATION_CODE_ERROR);
    }
}
