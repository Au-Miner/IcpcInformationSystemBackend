package com.IcpcInformationSystemBackend.service;


import com.IcpcInformationSystemBackend.model.request.RegisterSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ReigsterUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface RegisterService {
    Result getEmailCode(String emailAddress);

    Result registerSchool(RegisterSchoolInfo registerSchoolInfo);

    Result reigsterUser(ReigsterUserInfo reigsterUserInfo);

    String generateCode();

    String generateUserId();

    Result getSchoolIdAndName();

    int judgeEmailCode(String email, String emailCode);
}
