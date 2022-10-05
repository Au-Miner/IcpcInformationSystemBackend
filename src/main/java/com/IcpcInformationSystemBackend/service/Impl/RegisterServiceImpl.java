package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.EmailCodeDoMapper;
import com.IcpcInformationSystemBackend.dao.PasswordDoMapper;
import com.IcpcInformationSystemBackend.dao.SchoolDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.RegisterSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ReigsterUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.SchoolIdResponse;
import com.IcpcInformationSystemBackend.service.RegisterService;
import com.IcpcInformationSystemBackend.tools.EmailTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private SchoolDoMapper schoolDoMapper;

    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private PasswordDoMapper passwordDoMapper;

    @Resource
    private EmailTool emailTool;

    @Override
    public Result registerSchool(RegisterSchoolInfo registerSchoolInfo) {
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(registerSchoolInfo.getSchoolId());
        if (schoolDoMapper.countByExample(schoolDoExample) > 0)
            return ResultTool.error(EmAllException.SCHOOL_HAVE_REGISTERED);
        SchoolDo schoolDo = new SchoolDo();
        BeanUtils.copyProperties(registerSchoolInfo, schoolDo);
        schoolDo.setState(1);

        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(registerSchoolInfo.getUserEmail());
        if (userDoMapper.countByExample(userDoExample) > 0)
            return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(registerSchoolInfo, userDo);
        userDo.setIdentity(4);
        userDo.setState(1);

        PasswordDo passwordDo = new PasswordDo();
        passwordDo.setUserEmail(userDo.getUserEmail());
        passwordDo.setPasswd(registerSchoolInfo.getPasswd());

        switch (emailTool.judgeEmailCode(registerSchoolInfo.getUserEmail(), registerSchoolInfo.getEmailCode())) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_ERROR);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }

        if (schoolDoMapper.insertSelective(schoolDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (userDoMapper.insertSelective(userDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (passwordDoMapper.insertSelective(passwordDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }



    @Override
    public Result reigsterUser(ReigsterUserInfo reigsterUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(reigsterUserInfo.getUserEmail());
        if (userDoMapper.countByExample(userDoExample) > 0)
            return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);

        userDoExample.clear();
        userDoExample.createCriteria().andSchoolIdEqualTo(reigsterUserInfo.getSchoolId());
        if (userDoMapper.countByExample(userDoExample) == 0)
            return ResultTool.error(EmAllException.SCHOOL_HAVENOT_REGISTERED);

        switch (emailTool.judgeEmailCode(reigsterUserInfo.getUserEmail(), reigsterUserInfo.getEmailCode())) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_ERROR);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }

        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(reigsterUserInfo, userDo);
        userDo.setState(1);

        PasswordDo passwordDo = new PasswordDo();
        passwordDo.setUserEmail(userDo.getUserEmail());
        passwordDo.setPasswd(reigsterUserInfo.getPasswd());

        if (userDoMapper.insertSelective(userDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (passwordDoMapper.insertSelective(passwordDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result getSchoolIdAndName() {
        try {
            SchoolDoExample schoolDoExample = new SchoolDoExample();
            schoolDoExample.createCriteria().getAllCriteria();
            List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
            List<SchoolIdResponse> resList = new ArrayList<>();
            for (SchoolDo schoolDo : schoolDos) {
                SchoolIdResponse schoolIdResponse = new SchoolIdResponse();
                BeanUtils.copyProperties(schoolDo, schoolIdResponse);
                resList.add(schoolIdResponse);
            }
            return ResultTool.success(resList);
        }catch (Exception e) {
            return ResultTool.error(EmAllException.UNKNOWN_ERROR);
        }
    }
}
