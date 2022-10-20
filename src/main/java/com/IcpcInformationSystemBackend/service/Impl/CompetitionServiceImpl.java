package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.CompetitionDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.RegisterCompetitionInfo;
import com.IcpcInformationSystemBackend.model.response.CompetitionInfoResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Resource
    private CompetitionDoMapper competitionDoMapper;

    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private AuthTool authTool;

    @Override
    public Result buildCompetition(RegisterCompetitionInfo registerCompetitionInfo) {
        CompetitionDo competitionDo = new CompetitionDo();
        BeanUtils.copyProperties(registerCompetitionInfo, competitionDo);
        if (!Objects.equals(competitionDo.getCompetitionId(), ""))
            return ResultTool.error(EmAllException.BAD_REQUEST);
        competitionDo.setCompetitionId(generateCompetitionId());
        String userEmail = authTool.getUserId();
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (userDos.get(0).getUserState() != 2)
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        competitionDo.setBuilderEmail(userEmail);
        competitionDo.setCompetitionState(1);
        competitionDo.setSchoolId(userDos.get(0).getSchoolId());
        String duration = registerCompetitionInfo.getDuration();
        if (!judgeDuration(duration))
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (competitionDoMapper.insertSelective(competitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    public boolean judgeDuration(String duration) {
        if (duration.length() != 5)
            return false;
        if (duration.charAt(2) != ':')
            return false;
        if (duration.charAt(3) >= '6')
            return false;
        for (int i = 0; i < 5; i++) {
            if (i == 2)
                continue;
            if (duration.charAt(i) < '0' || duration.charAt(i) > '9')
                return false;
        }
        return true;
    }

    @Override
    public String generateCompetitionId() {
        while (true) {
            StringBuilder tmp = new StringBuilder();
            Calendar ca = Calendar.getInstance();
            tmp.append(ca.get(Calendar.YEAR));
            tmp = new StringBuilder(tmp.substring(2));
            if (ca.get(Calendar.MONTH) < 10)
                tmp.append(0);
            tmp.append(ca.get(Calendar.MONTH));
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < 6; i++)
                tmp.append(random.nextInt(10));
            CompetitionDoExample competitionDoExample = new CompetitionDoExample();
            competitionDoExample.createCriteria().andCompetitionIdEqualTo(String.valueOf(tmp));
            if (competitionDoMapper.countByExample(competitionDoExample) == 0)
                return String.valueOf(tmp);
        }
    }

    // state为3表示重新申请创建比赛，state为2表示申请修改比赛信息
    @Override
    public Result rebuildCompetition(RegisterCompetitionInfo registerCompetitionInfo, int state) {
        CompetitionDo competitionDo = new CompetitionDo();
        BeanUtils.copyProperties(registerCompetitionInfo, competitionDo);
        if (Objects.equals(competitionDo.getCompetitionId(), ""))
            return ResultTool.error(EmAllException.BAD_REQUEST);
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionDo.getCompetitionId());
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        if (competitionDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (competitionDos.get(0).getCompetitionState() != state)
            return ResultTool.error(EmAllException.COMPETITION_STATE_ERROR);
        if (!Objects.equals(competitionDos.get(0).getBuilderEmail(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        competitionDo.setCompetitionState(1);
        competitionDo.setApproveReason("");
        if (competitionDoMapper.updateByPrimaryKeySelective(competitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result getOwnCompetitionInfo() {
        String userEmail = authTool.getUserId();
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andBuilderEmailEqualTo(userEmail);
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        ArrayList<CompetitionInfoResponse> res = new ArrayList<>();
        for (CompetitionDo competitionDo : competitionDos) {
            CompetitionInfoResponse competitionInfoResponse = new CompetitionInfoResponse();
            BeanUtils.copyProperties(competitionDo, competitionInfoResponse);
            res.add(competitionInfoResponse);
        }
        return ResultTool.success(res);
    }

    @Override
    public Result getAllCompetitionInfo() {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().getAllCriteria();
        ArrayList<CompetitionInfoResponse> res = new ArrayList<>();
        for (CompetitionDo competitionDo : competitionDoMapper.selectByExample(competitionDoExample)) {
            CompetitionInfoResponse competitionInfoResponse = new CompetitionInfoResponse();
            BeanUtils.copyProperties(competitionDo, competitionInfoResponse);
            UserDoExample userDoExample = new UserDoExample();
            userDoExample.createCriteria().andUserEmailEqualTo(competitionDo.getBuilderEmail());
            List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
            if (userDos.isEmpty())
                return ResultTool.error(EmAllException.DATABASE_ERR);
            BeanUtils.copyProperties(userDos.get(0), competitionInfoResponse);
            res.add(competitionInfoResponse);
        }
        return ResultTool.success(res);
    }

    @Override
    public Result getAllAcceptCompetitionInfo() {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().getAllCriteria();
        ArrayList<CompetitionInfoResponse> res = new ArrayList<>();
        for (CompetitionDo competitionDo : competitionDoMapper.selectByExample(competitionDoExample)) {
            if (competitionDo.getCompetitionState() != 2)
                continue;
            CompetitionInfoResponse competitionInfoResponse = new CompetitionInfoResponse();
            BeanUtils.copyProperties(competitionDo, competitionInfoResponse);
            UserDoExample userDoExample = new UserDoExample();
            userDoExample.createCriteria().andUserEmailEqualTo(competitionDo.getBuilderEmail());
            List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
            if (userDos.isEmpty())
                return ResultTool.error(EmAllException.DATABASE_ERR);
            BeanUtils.copyProperties(userDos.get(0), competitionInfoResponse);
            res.add(competitionInfoResponse);
        }
        return ResultTool.success(res);
    }
}
