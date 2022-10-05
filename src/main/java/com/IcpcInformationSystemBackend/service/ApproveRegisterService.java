package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.response.Result;

public interface ApproveRegisterService {
    Result getSchoolRegitsterInfo();
    Result getCoachRegitsterInfo();
    Result getStudentRegitsterInfo();
}
