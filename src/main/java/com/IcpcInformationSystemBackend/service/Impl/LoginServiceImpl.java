package com.IcpcInformationSystemBackend.service.Impl;


import com.IcpcInformationSystemBackend.dao.PasswordDoMapper;
import com.IcpcInformationSystemBackend.dao.SchoolDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.LoginUserInfo;
import com.IcpcInformationSystemBackend.model.response.LoginResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.LoginService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.EmailTool;
import com.IcpcInformationSystemBackend.tools.JwtTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private PasswordDoMapper passwordDoMapper;

    @Resource
    private SchoolDoMapper schoolDoMapper;

    @Resource
    private EmailTool emailTool;

    @Resource
    private AuthTool authTool;

    @Resource
    private JwtTool jwtTool;

    @Override
    public Result loginUser(LoginUserInfo loginUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(loginUserInfo.getKey());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        PasswordDoExample passwordDoExample = new PasswordDoExample();
        passwordDoExample.createCriteria().andUserEmailEqualTo(loginUserInfo.getKey());
        List<PasswordDo> passwordDos = passwordDoMapper.selectByExample(passwordDoExample);
        if (passwordDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!Objects.equals(passwordDos.get(0).getPasswd(), loginUserInfo.getPassword()))
            return ResultTool.error(EmAllException.PASSWD_WRONG);
        if (!Objects.equals(loginUserInfo.getIdentity(), userDos.get(0).getIdentity()))
            return ResultTool.error(EmAllException.USER_IDENTITY_ERROR);
        if (userDos.get(0).getUserState() != 2)
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        LoginResponse loginResponse = new LoginResponse();
        BeanUtils.copyProperties(userDos.get(0), loginResponse);
        loginResponse.setToken(jwtTool.createJwt(
                userDos.get(0).getUserEmail(), //邮箱
                userDos.get(0).getChiName()    //中文名
        ));
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(userDos.get(0).getSchoolId());
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        loginResponse.setChiSchoolName(schoolDos.get(0).getChiSchoolName());
        return ResultTool.success(loginResponse);
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
                return ResultTool.error(EmAllException.EMAIL_CODE_WRONG);
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

    @Override
    public Result modifyPassword(String email, String emailCode, String newPassword) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(email);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        int mark = emailTool.judgeEmailCode(email, emailCode);
        switch (mark) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_WRONG);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }
        PasswordDo passwordDo = new PasswordDo();
        passwordDo.setPasswd(newPassword);
        passwordDo.setUserEmail(email);
        if (passwordDoMapper.updateByPrimaryKeySelective(passwordDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }
}
