package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.response.Result;

public interface LoginService {

    Result loginUser(String key, String password);

    Result forgetUser(String email, String emailCode);
}
