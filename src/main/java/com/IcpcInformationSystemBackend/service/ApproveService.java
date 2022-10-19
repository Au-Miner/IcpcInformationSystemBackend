package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.ApproveCompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveTeamInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface ApproveService {
    Result getSchoolRegitsterInfo();

    Result getCoachRegitsterInfo();

    Result getStudentRegitsterInfo();

    Result getTeamInfoByCompetitionId(String competitionId);

    Result approveSchoolRegister(ApproveSchoolInfo approveSchoolInfo);

    Result approveCoachRegister(ApproveUserInfo approveUserInfo);

    Result approveStudentRegister(ApproveUserInfo approveUserInfo);

    Result approveCompetitionRegister(ApproveCompetitionInfo approveCompetitionInfo);

    Result approveTeamInfoByTeamId(ApproveTeamInfo approveTeamInfo);
}
