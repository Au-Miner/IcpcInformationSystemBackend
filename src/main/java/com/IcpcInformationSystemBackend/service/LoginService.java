package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.LoginUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface LoginService {

    Result loginUser(LoginUserInfo loginUserInfo);

    Result forgetUser(String email, String emailCode, String idCard);

    Result modifyPassword(String email, String emailCode, String idCard, String newPassword);
}
