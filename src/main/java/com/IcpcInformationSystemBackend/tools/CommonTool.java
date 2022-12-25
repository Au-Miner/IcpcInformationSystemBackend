package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.dao.*;
import com.IcpcInformationSystemBackend.model.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private PositionDoMapper positionDoMapper;

    @Resource
    private TeamScoreDoMapper teamScoreDoMapper;

    @Resource
    private AuthTool authTool;

    public SchoolDo getSchoolDoBySchoolId(String schoolId) {
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        if (schoolDos.isEmpty())
            return null;
        return schoolDos.get(0);
    }

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

    public String getEngNameByUserEmail(String userEmail) {
        if (Objects.equals(userEmail, ""))
            return "";
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return "";
        return userDos.get(0).getEngName();
    }

    public CompetitionDo getCompetitionDoByCompetitionId(String competitionId) {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        if (competitionDos.isEmpty())
            return null;
        return competitionDos.get(0);
    }

    public String getTeamIdByCompetitionIdAndUserEmail(String competitionId, String userEmail) {
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

    public List<TeamDo> getTeamsByCompetitionId(String competitionId) {
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        return teamDoMapper.selectByExample(teamDoExample);
    }

    public PositionDo getPositionByPositionId(String positionId) {
        PositionDoExample positionDoExample = new PositionDoExample();
        positionDoExample.createCriteria().andPositionIdEqualTo(positionId);
        List<PositionDo> positionDos = positionDoMapper.selectByExample(positionDoExample);
        if (positionDos.isEmpty())
            return null;
        return positionDos.get(0);
    }

    public TeamScoreDo getTeamScoreByCompetitionIdAndTeamId(String competitionId, String teamId) {
        TeamScoreDoExample teamScoreDoExample = new TeamScoreDoExample();
        teamScoreDoExample.createCriteria().andTeamIdEqualTo(teamId).andCompetitionIdEqualTo(competitionId);
        List<TeamScoreDo> teamScoreDos = teamScoreDoMapper.selectByExample(teamScoreDoExample);
        if (teamScoreDos.isEmpty())
            return null;
        return teamScoreDos.get(0);
    }

    public List<PositionDo> getPositionsByCompetitionId(String competitionId) {
        PositionDoExample positionDoExample = new PositionDoExample();
        positionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        return positionDoMapper.selectByExample(positionDoExample);
    }

    public int getUserParticipateInIcpcRegionalCompetitionTimes(String year, String userEmail) {
        UserCompetitionDoExample userCompetitionDoExample = new UserCompetitionDoExample();
        userCompetitionDoExample.createCriteria().andStudentEmailEqualTo(userEmail);
        List<UserCompetitionDo> userCompetitionDos = userCompetitionDoMapper.selectByExample(userCompetitionDoExample);
        int times = 0;
        for (UserCompetitionDo userCompetitionDo : userCompetitionDos) {
            String teamId = userCompetitionDo.getTeamId();
            String competitionId = userCompetitionDo.getCompetitionId();
            if (judgeCompetitionIfIcpcRegionalCompetition(competitionId)) {
                TeamDo teamDo = getTeamByCompetitionIdAndTeamId(competitionId, teamId);
                if (teamDo.getType() != 2) //针对icpc区域赛非打星参赛次数计数
                    times++;
            }
        }
        return times;
    }

    public boolean judgeUserEmailIfExists(String userEmail) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        return !userDos.isEmpty();
    }

    public boolean judgeUserStateIfRight(String userEmail) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return false;
        return userDos.get(0).getUserState() == 2;
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

    public boolean judgeCompetitionChairmanIdentityIfRight(String competitionId, String userEmail) {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        if (competitionDos.isEmpty())
            return false;
        return Objects.equals(competitionDos.get(0).getBuilderEmail(), userEmail);
    }

    public boolean judgeSchoolStateIfPass(String schoolId) {
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        if (schoolDos.isEmpty())
            return false;
        return schoolDos.get(0).getSchoolState() == 2;
    }

    public int judgeTeamRegisterIfRightAboutCompetitionTime(String competitionId) {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        if (competitionDos.isEmpty())
            return -1;
        Date startTime = competitionDos.get(0).getRegistrationStartTime();
        Date endTime = competitionDos.get(0).getRegistrationEndTime();
        Date now = new Date();
        if (now.before(startTime))
            return 1;
        if (now.after(endTime))
            return 2;
        return 0;
    }

    public boolean judgeTeamIfExists(String competitionId, String teamId) {
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andTeamIdEqualTo(teamId).andCompetitionIdEqualTo(competitionId);
        List<TeamDo> teamDos = teamDoMapper.selectByExample(teamDoExample);
        return !teamDos.isEmpty();
    }
    public boolean judgeTeamStateIfRight(String competitionId, String teamId) {
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andTeamIdEqualTo(teamId).andCompetitionIdEqualTo(competitionId);
        List<TeamDo> teamDos = teamDoMapper.selectByExample(teamDoExample);
        if (teamDos.isEmpty())
            return false;
        return teamDos.get(0).getTeamState() == 4;
    }

    public boolean judgePositionIfExists(String positionId) {
        PositionDoExample positionDoExample = new PositionDoExample();
        positionDoExample.createCriteria().andPositionIdEqualTo(positionId);
        List<PositionDo> positionDos = positionDoMapper.selectByExample(positionDoExample);
        return !positionDos.isEmpty();
    }

    public boolean judgeCompetitionIfIcpcRegionalCompetition(String competitionId) {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        if (competitionDos.isEmpty())
            return false;
        return competitionDos.get(0).getIfIcpcRegionalCompetition() == 1;
    }

    public boolean judgeUserIdentityIfStudent(String userEmail) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        return !userDos.isEmpty() && userDos.get(0).getIdentity() == 1;
    }

    public boolean judgeUserIdentityIfCoach(String userEmail) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(userEmail);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        return !userDos.isEmpty() && userDos.get(0).getIdentity() == 2;
    }



}
