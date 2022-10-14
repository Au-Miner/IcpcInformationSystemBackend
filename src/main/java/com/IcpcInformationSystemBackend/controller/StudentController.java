package com.IcpcInformationSystemBackend.controller;

import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.ApproveService;
import com.IcpcInformationSystemBackend.service.CompetitionService;
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
@RequestMapping("/student")
@Api(tags = "选手接口类")
public class StudentController {
    @Resource
    private ApproveService approveService;

    @Resource
    private CompetitionService competitionService;

    @GetMapping("/getAcceptCompetitionInfo")
    @ApiOperation(value = "选手获取所有已批准通过比赛信息")
    public Result getAcceptCompetitionInfo() {
        return competitionService.getAllAcceptCompetitionInfo();
    }
}
