package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.request.ApproveUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.ApproveService;
import com.IcpcInformationSystemBackend.service.CompetitionService;
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
@RequestMapping("/coach")
@Api(tags = "教练接口类")
public class CoachController {
    @Resource
    private ApproveService approveService;

    @Resource
    private EmailService emailService;

    @Resource
    private CompetitionService competitionService;

    @GetMapping("/getStudentRegitsterInfo")
    @ApiOperation(value = "教练在审核学生时在此接口获取选手所有相关信息")
    public Result getStudentRegitsterInfo() {
        return approveService.getStudentRegitsterInfo();
    }

    @PostMapping("/approveStudentRegister")
    @ApiOperation(value = "学校负责人审批选手注册账号")
    public Result approveStudentRegister(@ApiParam(name = "审批选手时需要提供的信息", required = true) @Validated @RequestBody ApproveUserInfo approveUserInfo) {
        Result res1 = approveService.approveStudentRegister(approveUserInfo);
        if (res1.getCode() != 200)
            return res1;
        Result res2 = emailService.sendEmailMessage(approveUserInfo.getUserEmail(), approveUserInfo.getApproveReason());
        if (res2.getCode() != 200)
            return res2;
        return ResultTool.success();
    }

    @GetMapping("/getAcceptCompetitionInfo")
    @ApiOperation(value = "教练获取所有已批准通过比赛信息")
    public Result getAcceptCompetitionInfo() {
        return competitionService.getAllAcceptCompetitionInfo();
    }
}
