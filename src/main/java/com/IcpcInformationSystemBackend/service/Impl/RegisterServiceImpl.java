package com.IcpcInformationSystemBackend.service.Impl;

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
import com.IcpcInformationSystemBackend.tools.IdCardTool;
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

    @Resource
    private IdCardTool idCardTool;

    @Override
    public Result registerSchool(RegisterSchoolInfo registerSchoolInfo) {
        switch (emailTool.judgeEmailCode(registerSchoolInfo.getUserEmail(), registerSchoolInfo.getEmailCode())) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_WRONG);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(registerSchoolInfo.getSchoolId());
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        if (!schoolDos.isEmpty()) {
            // if (schoolDos.get(0).getSchoolState() != 3)
            //     return ResultTool.error(EmAllException.SCHOOL_HAVE_REGISTERED);
            if (schoolDoMapper.deleteByExample(schoolDoExample) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
        }
        SchoolDo schoolDo = new SchoolDo();
        BeanUtils.copyProperties(registerSchoolInfo, schoolDo);
        schoolDo.setSchoolState(1);

        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(registerSchoolInfo.getUserEmail());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (!userDos.isEmpty()) {
            // if (userDos.get(0).getUserState() != 3)
            //     return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);
            if (userDoMapper.deleteByExample(userDoExample) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            if (passwordDoMapper.deleteByPrimaryKey(registerSchoolInfo.getUserEmail()) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(registerSchoolInfo, userDo);
        userDo.setIdentity(4);
        userDo.setUserState(1);

        PasswordDo passwordDo = new PasswordDo();
        passwordDo.setUserEmail(userDo.getUserEmail());
        passwordDo.setPasswd(registerSchoolInfo.getPasswd());

        if (!Objects.equals(registerSchoolInfo.getIdCard(), "") && !idCardTool.judgeIdCardFormatIfRight(registerSchoolInfo.getIdCard()))
            return ResultTool.error(EmAllException.ID_CARD_FORMAT_ERROR);
        // if (idCardTool.judgeIdCardIfHasRegistered(registerSchoolInfo.getIdCard()))
        //     return ResultTool.error(EmAllException.ID_CARD_HAS_REGISTERED);

        if (schoolDoMapper.insertSelective(schoolDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (userDoMapper.insertSelective(userDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        log.info(passwordDo.getPasswd());
        log.info(passwordDo.getUserEmail());
        log.info("11111111111111111");
        if (passwordDoMapper.insertSelective(passwordDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }



    @Override
    public Result reigsterUser(ReigsterUserInfo reigsterUserInfo) {
        switch (emailTool.judgeEmailCode(reigsterUserInfo.getUserEmail(), reigsterUserInfo.getEmailCode())) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_WRONG);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }
        if (reigsterUserInfo.getIdentity() != 1 && reigsterUserInfo.getIdentity() != 2)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(reigsterUserInfo.getUserEmail());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (!userDos.isEmpty()) {
            // if (userDos.get(0).getUserState() != 3)
            //     return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);
            if (userDoMapper.deleteByExample(userDoExample) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
            if (passwordDoMapper.deleteByPrimaryKey(reigsterUserInfo.getUserEmail()) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
        }

        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(reigsterUserInfo.getSchoolId());
        if (schoolDoMapper.countByExample(schoolDoExample) == 0)
            return ResultTool.error(EmAllException.SCHOOL_NOT_REGISTERED);

        if (reigsterUserInfo.getIdentity() == 1 && !idCardTool.judgeIdCardFormatIfRight(reigsterUserInfo.getIdCard()))
            return ResultTool.error(EmAllException.ID_CARD_FORMAT_ERROR);
        else if (reigsterUserInfo.getIdentity() == 2 && !Objects.equals(reigsterUserInfo.getIdCard(), "") && !idCardTool.judgeIdCardFormatIfRight(reigsterUserInfo.getIdCard()))
            return ResultTool.error(EmAllException.ID_CARD_FORMAT_ERROR);
        if (reigsterUserInfo.getIdentity() == 1 && idCardTool.judgeIdCardIfHasRegistered(reigsterUserInfo.getIdCard()))
            return ResultTool.error(EmAllException.ID_CARD_HAS_REGISTERED);
        else if (reigsterUserInfo.getIdentity() == 2 && !Objects.equals(reigsterUserInfo.getIdCard(), "") && idCardTool.judgeIdCardIfHasRegistered(reigsterUserInfo.getIdCard()))
            return ResultTool.error(EmAllException.ID_CARD_HAS_REGISTERED);

        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(reigsterUserInfo, userDo);
        userDo.setUserState(1);

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
                if (schoolDo.getSchoolState() == 2) {
                    SchoolIdResponse schoolIdResponse = new SchoolIdResponse();
                    BeanUtils.copyProperties(schoolDo, schoolIdResponse);
                    resList.add(schoolIdResponse);
                }
            }
            return ResultTool.success(resList);
        }catch (Exception e) {
            return ResultTool.error(EmAllException.UNKNOWN_ERROR);
        }
    }
}
