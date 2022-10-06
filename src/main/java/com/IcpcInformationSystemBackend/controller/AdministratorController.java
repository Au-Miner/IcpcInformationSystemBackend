package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.request.ApproveSchoolInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.ApproveRegisterService;
import com.IcpcInformationSystemBackend.service.EmailService;
import com.IcpcInformationSystemBackend.tools.ResultTool;
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
@RequestMapping("/administrator")
@Api(tags = "系统管理员接口类")
public class AdministratorController {
    @Resource
    private ApproveRegisterService approveRegisterService;

    @Resource
    private EmailService emailService;

    @GetMapping("/getSchoolRegitsterInfo")
    @ApiOperation(value = "系统管理员在审核学校及学校负责人时在此接口获取学校所有相关信息")
    public Result getSchoolRegitsterInfo() {
        return approveRegisterService.getSchoolRegitsterInfo();
    }

    @PostMapping("/approveSchoolRegister")
    @ApiOperation(value = "系统管理员审批学校及学校负责人注册账号")
    public Result approveSchoolRegister(@ApiParam(name = "审批学校时需要提供的信息", required = true) @Validated @RequestBody ApproveSchoolInfo approveSchoolInfo) {
        Result res1 = approveRegisterService.approveSchoolRegister(approveSchoolInfo);
        if (res1.getCode() != 200)
            return res1;
        Result res2 = emailService.sendEmailMessage(approveSchoolInfo.getUserEmail(), approveSchoolInfo.getApproveReason());
        if (res2.getCode() != 200)
            return res2;
        return ResultTool.success();
    }
}
