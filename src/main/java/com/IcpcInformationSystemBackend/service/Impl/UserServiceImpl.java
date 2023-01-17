package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.CompetitionDoMapper;
import com.IcpcInformationSystemBackend.dao.SchoolDoMapper;
import com.IcpcInformationSystemBackend.dao.UserCompetitionDoMapper;
import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.response.CompetitionInfoResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.UserInfoResponse;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.service.UserService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.CommonTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private AuthTool authTool;

    @Resource
    private UserCompetitionDoMapper userCompetitionDoMapper;

    @Resource
    private CommonTool commonTool;

    @Override
    public Result getSelfUserInfo() {
        UserDo userDo;
        try {
            userDo = authTool.getUser();
        } catch (AllException e) {
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userDo, userInfoResponse);
        return ResultTool.success(userInfoResponse);
    }

    @Override
    public Result getSelfCompetitionInfo() {
        UserCompetitionDoExample userCompetitionDoExample = new UserCompetitionDoExample();
        userCompetitionDoExample.createCriteria().andStudentEmailEqualTo(authTool.getUserId());
        List<UserCompetitionDo> userCompetitionDos = userCompetitionDoMapper.selectByExample(userCompetitionDoExample);
        ArrayList<CompetitionInfoResponse> res = new ArrayList<>();
        for (UserCompetitionDo userCompetitionDo : userCompetitionDos) {
            CompetitionDo competitionDo = commonTool.getCompetitionDoByCompetitionId(userCompetitionDo.getCompetitionId());
            CompetitionInfoResponse competitionInfoResponse = new CompetitionInfoResponse();
            BeanUtils.copyProperties(competitionDo, competitionInfoResponse);
            res.add(competitionInfoResponse);
        }
        return ResultTool.success(res);
    }
}
