package com.IcpcInformationSystemBackend.controller;


import com.IcpcInformationSystemBackend.model.request.RegisterSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ReigsterUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Resource
    RegisterService registerService;

    @GetMapping("/getEmailCode")
    public Result getEmailCode(String emailAddress) {
        return registerService.getEmailCode(emailAddress);
    }

    @PostMapping("/registerSchool")
    public Result registerSchool(@Validated @RequestBody RegisterSchoolInfo registerSchoolInfo) {
        return registerService.registerSchool(registerSchoolInfo);
    }

    @PostMapping("/registerUser")
    public Result reigsterUser(@Validated @RequestBody ReigsterUserInfo reigsterUserInfo) {
        return registerService.reigsterUser(reigsterUserInfo);
    }

    @GetMapping("/getSchoolIdAndName")
    public Result getSchoolIdAndName() {
        return registerService.getSchoolIdAndName();
    }
}
