package com.IcpcInformationSystemBackend.controller;


import com.IcpcInformationSystemBackend.model.request.RegisterSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ReigsterUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.RegisterService;
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
@RequestMapping("/register")
@Api(tags = "注册接口类")
public class RegisterController {
    @Resource
    private RegisterService registerService;

    @PostMapping("/registerSchool")
    @ApiOperation(value = "学校注册接口，学校注册的同时也需要注册学校负责人，因此需要提供比较多的信息")
    public Result registerSchool(@ApiParam(name = "学校注册需要提供的信息", required = true) @Validated @RequestBody RegisterSchoolInfo registerSchoolInfo) {
        return registerService.registerSchool(registerSchoolInfo);
    }

    @PostMapping("/registerUser")
    @ApiOperation(value = "用户注册接口")
    public Result reigsterUser(@ApiParam(name = "用户注册需要提供的信息", required = true) @Validated @RequestBody ReigsterUserInfo reigsterUserInfo) {
        return registerService.reigsterUser(reigsterUserInfo);
    }

    @GetMapping("/getSchoolIdAndName")
    @ApiOperation(value = "在用户注册时需要选择学校，用此接口获取所有已经注册学校的信息")
    public Result getSchoolIdAndName() {
        return registerService.getSchoolIdAndName();
    }
}
