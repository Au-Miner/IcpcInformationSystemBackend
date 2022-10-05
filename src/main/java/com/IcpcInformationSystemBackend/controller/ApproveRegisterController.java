package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.ApproveRegisterService;
import com.IcpcInformationSystemBackend.service.EmailService;
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
@RequestMapping("/approveRegister")
@Api(tags = "审批注册接口类")
public class ApproveRegisterController {
    @Resource
    ApproveRegisterService approveRegisterService;

    @GetMapping("/getSchoolRegitsterInfo")
    @ApiOperation(value = "系统管理员审核学校及学校负责人，在此接口获取学校所有相关信息")
    public Result getSchoolRegitsterInfo() {
        return approveRegisterService.getSchoolRegitsterInfo();
    }

    @GetMapping("/getCoachRegitsterInfo")
    @ApiOperation(value = "学校负责人审核教练，在此接口获取学生所有相关信息")
    public Result getCoachRegitsterInfo() {
        return approveRegisterService.getCoachRegitsterInfo();
    }

    @GetMapping("/getStudentRegitsterInfo")
    @ApiOperation(value = "学校负责人和教练审核学生，在此接口获取教练所有相关信息")
    public Result getStudentRegitsterInfo() {
        return approveRegisterService.getStudentRegitsterInfo();
    }
}
