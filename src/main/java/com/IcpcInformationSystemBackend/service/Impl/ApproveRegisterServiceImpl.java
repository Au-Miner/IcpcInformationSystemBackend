package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.SchoolDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.SchoolDo;
import com.IcpcInformationSystemBackend.model.entity.SchoolDoExample;
import com.IcpcInformationSystemBackend.model.entity.UserDo;
import com.IcpcInformationSystemBackend.model.entity.UserDoExample;
import com.IcpcInformationSystemBackend.model.request.ApproveSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.SchoolInfoResponse;
import com.IcpcInformationSystemBackend.model.response.UserInfoResponse;
import com.IcpcInformationSystemBackend.service.ApproveRegisterService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ApproveRegisterServiceImpl implements ApproveRegisterService {
    @Resource
    private SchoolDoMapper schoolDoMapper;

    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private AuthTool authTool;

    @Override
    public Result getSchoolRegitsterInfo() {
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().getAllCriteria();
        List<SchoolInfoResponse> resList = new ArrayList<>();
        for (SchoolDo schoolDo : schoolDoMapper.selectByExample(schoolDoExample)) {
            SchoolInfoResponse schoolInfoResponse = new SchoolInfoResponse();
            BeanUtils.copyProperties(schoolDo, schoolInfoResponse);
            UserDoExample userDoExample = new UserDoExample();
            userDoExample.createCriteria().andSchoolIdEqualTo(schoolDo.getSchoolId()).andIdentityEqualTo(4);
            for (UserDo userDo : userDoMapper.selectByExample(userDoExample)) {
                BeanUtils.copyProperties(userDo, schoolInfoResponse);
            }
            resList.add(schoolInfoResponse);
        }
        return ResultTool.success(resList);
    }

    @Override
    public Result getCoachRegitsterInfo() {
        String userEmail = authTool.getUserId();
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.TOKEN_ERROR);
        String schoolId = userDos.get(0).getSchoolId();
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        if (schoolDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_SCHOOL);
        userDoExample.clear();
        userDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<UserInfoResponse> resList = new ArrayList<>();
        for (UserDo userDo : userDoMapper.selectByExample(userDoExample)) {
            if (userDo.getIdentity() == 2) {
                UserInfoResponse userInfoResponse = new UserInfoResponse();
                BeanUtils.copyProperties(userDo, userInfoResponse);
                BeanUtils.copyProperties(schoolDos.get(0), userInfoResponse);
                resList.add(userInfoResponse);
            }
        }
        return ResultTool.success(resList);
    }

    @Override
    public Result getStudentRegitsterInfo() {
        String userEmail = authTool.getUserId();
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.TOKEN_ERROR);
        String schoolId = userDos.get(0).getSchoolId();
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        if (schoolDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_SCHOOL);
        userDoExample.clear();
        userDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<UserInfoResponse> resList = new ArrayList<>();
        for (UserDo userDo : userDoMapper.selectByExample(userDoExample)) {
            if (userDo.getIdentity() == 1) {
                UserInfoResponse userInfoResponse = new UserInfoResponse();
                BeanUtils.copyProperties(userDo, userInfoResponse);
                BeanUtils.copyProperties(schoolDos.get(0), userInfoResponse);
                resList.add(userInfoResponse);
            }
        }
        return ResultTool.success(resList);
    }

    @Override
    public Result approveSchoolRegister(ApproveSchoolInfo approveSchoolInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(approveSchoolInfo.getUserEmail());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (userDos.get(0).getState() != 1)
            return ResultTool.error(EmAllException.USER_DONT_NEED_APPROVE);
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(approveSchoolInfo.getSchoolId());
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        if (schoolDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_SCHOOL);
        if (schoolDos.get(0).getState() != 1)
            return ResultTool.error(EmAllException.SCHOOL_DONT_NEED_APPROVE);
        log.info("审批结果：" + approveSchoolInfo.getApproveResult());
        if (approveSchoolInfo.getApproveResult() != 2 && approveSchoolInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        schoolDos.get(0).setState(approveSchoolInfo.getApproveResult());
        userDos.get(0).setState(approveSchoolInfo.getApproveResult());
        if (schoolDoMapper.updateByPrimaryKeySelective(schoolDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (userDoMapper.updateByPrimaryKeySelective(userDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result approveCoachRegister(ApproveUserInfo approveUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(approveUserInfo.getUserEmail());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (userDos.get(0).getState() != 1)
            return ResultTool.error(EmAllException.USER_DONT_NEED_APPROVE);
        if (userDos.get(0).getIdentity() != 2)
            return ResultTool.error(EmAllException.USER_IDENTITY_ERROR);
        if (approveUserInfo.getApproveResult() != 2 && approveUserInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        userDos.get(0).setState(approveUserInfo.getApproveResult());
        if (userDoMapper.updateByPrimaryKeySelective(userDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result approveStudentRegister(ApproveUserInfo approveUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(approveUserInfo.getUserEmail());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (userDos.get(0).getState() != 1)
            return ResultTool.error(EmAllException.USER_DONT_NEED_APPROVE);
        if (userDos.get(0).getIdentity() != 1)
            return ResultTool.error(EmAllException.USER_IDENTITY_ERROR);
        if (approveUserInfo.getApproveResult() != 2 && approveUserInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        userDos.get(0).setState(approveUserInfo.getApproveResult());
        if (userDoMapper.updateByPrimaryKeySelective(userDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }
}
