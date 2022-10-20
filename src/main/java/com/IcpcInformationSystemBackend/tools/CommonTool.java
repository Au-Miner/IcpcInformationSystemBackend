package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.dao.*;
import com.IcpcInformationSystemBackend.model.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class CommonTool {
    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private SchoolDoMapper schoolDoMapper;

    @Resource
    private CompetitionDoMapper competitionDoMapper;

    @Resource
    private TeamDoMapper teamDoMapper;

    @Resource
    private UserCompetitionDoMapper userCompetitionDoMapper;

    public String getSchoolIdByUserEmail(String userEmail) {
        if (Objects.equals(userEmail, ""))
            return "";
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return "";
        return userDos.get(0).getSchoolId();
    }

    public String getChiNameByUserEmail(String userEmail) {
        if (Objects.equals(userEmail, ""))
            return "";
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return "";
        return userDos.get(0).getChiName();
    }

    public String getTeamIdByUserEmailAndCompetitionId(String userEmail, String competitionId) {
        if (Objects.equals(userEmail, "") || Objects.equals(competitionId, ""))
            return "";
        UserCompetitionDoExample userCompetitionDoExample = new UserCompetitionDoExample();
        userCompetitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId).andStudentEmailEqualTo(userEmail);
        List<UserCompetitionDo> userCompetitionDos = userCompetitionDoMapper.selectByExample(userCompetitionDoExample);
        if (userCompetitionDos.isEmpty())
            return "";
        return userCompetitionDos.get(0).getTeamId();
    }

    public TeamDo getTeamByCompetitionIdAndTeamId(String competitionId, String teamId) {
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andCompetitionIdEqualTo(competitionId).andTeamIdEqualTo(teamId);
        List<TeamDo> teamDos = teamDoMapper.selectByExample(teamDoExample);
        if (teamDos.isEmpty())
            return null;
        return teamDos.get(0);
    }

    public boolean judgeUserEmailIfExists(String userEmail) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        return !userDos.isEmpty();
    }

    public boolean judgeSchoolIdIfExists(String schoolId) {
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        return !schoolDos.isEmpty();
    }

    public boolean judgeCompetitionIdIfExists(String competitionId) {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        return !competitionDos.isEmpty();
    }

    public boolean judgTeamIfExists(String competitionId, String teamId) {
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andCompetitionIdEqualTo(competitionId).andTeamIdEqualTo(teamId);
        List<TeamDo> teamDos = teamDoMapper.selectByExample(teamDoExample);
        return !teamDos.isEmpty();
    }

    public boolean judgeUserIfHasSignUp4Competition(String userEmail, String competitionId) {
        UserCompetitionDoExample userCompetitionDoExample = new UserCompetitionDoExample();
        userCompetitionDoExample.createCriteria().andStudentEmailEqualTo(userEmail).andCompetitionIdEqualTo(competitionId);
        List<UserCompetitionDo> userCompetitionDos = userCompetitionDoMapper.selectByExample(userCompetitionDoExample);
        return !userCompetitionDos.isEmpty();
    }

    public boolean judgeCompetitionStateIfPass(String competitionId) {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        if (competitionDos.isEmpty())
            return false;
        return competitionDos.get(0).getCompetitionState() == 2;
    }
}
