package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.CompetitionDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.CompetitionInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Result buildCompetition(CompetitionInfo competitionInfo) {
        CompetitionDo competitionDo = new CompetitionDo();
        BeanUtils.copyProperties(competitionInfo, competitionDo);
        if (!Objects.equals(competitionDo.getCompetitionId(), "")) {
            log.info("比赛id号非空！");
            return ResultTool.error(EmAllException.BAD_REQUEST);
        }
        competitionDo.setCompetitionId(generateCompetitionId());
        String userEmail = authTool.getUserId();
        competitionDo.setBuilderEmail(userEmail);
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (userDos.get(0).getState() == 1)
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        competitionDo.setBuilderEmail(userEmail);
        competitionDo.setState(1);
        competitionDo.setSchoolId(userDos.get(0).getSchoolId());
        String duration = competitionInfo.getDuration();
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
}
