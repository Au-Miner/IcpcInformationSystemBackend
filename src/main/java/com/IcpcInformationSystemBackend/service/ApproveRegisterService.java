package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.ApproveSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface ApproveRegisterService {
    Result getSchoolRegitsterInfo();

    Result getCoachRegitsterInfo();

    Result getStudentRegitsterInfo();

    Result approveSchoolRegister(ApproveSchoolInfo approveSchoolInfo);

    Result approveCoachRegister(ApproveUserInfo approveUserInfo);

    Result approveStudentRegister(ApproveUserInfo approveUserInfo);
}
