package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.EmailMessageInfo;
import com.IcpcInformationSystemBackend.model.request.LoginUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface EmailService {
    Result sendEmailCode(String emailAddress);

    Result sendEmailMessage(EmailMessageInfo emailMessageInfo);

    String generateCode();
}
