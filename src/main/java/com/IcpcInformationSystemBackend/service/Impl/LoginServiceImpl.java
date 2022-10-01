package com.IcpcInformationSystemBackend.service.Impl;


import com.IcpcInformationSystemBackend.dao.PasswordDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.LoginService;
import com.IcpcInformationSystemBackend.tools.EmailTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserDoMapper userDoMapper;

    @Resource
    PasswordDoMapper passwordDoMapper;

    @Resource
    EmailTool emailTool;

    @Override
    public Result loginUser(String key, String password) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(key);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        PasswordDoExample passwordDoExample = new PasswordDoExample();
        passwordDoExample.createCriteria().andUserEmailEqualTo(key);
        List<PasswordDo> passwordDos = passwordDoMapper.selectByExample(passwordDoExample);
        if (passwordDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!Objects.equals(passwordDos.get(0).getPasswd(), password))
            return ResultTool.error(EmAllException.PASSWD_ERROR);
        return ResultTool.success();
    }


    @Override
    public Result forgetUser(String email, String emailCode) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(email);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        int mark = emailTool.judgeEmailCode(email, emailCode);
        switch (mark) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_ERROR);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }
        PasswordDoExample passwordDoExample = new PasswordDoExample();
        passwordDoExample.createCriteria().andUserEmailEqualTo(email);
        List<PasswordDo> passwordDos = passwordDoMapper.selectByExample(passwordDoExample);
        if (passwordDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        return ResultTool.success(passwordDos.get(0).getPasswd());
    }
}
