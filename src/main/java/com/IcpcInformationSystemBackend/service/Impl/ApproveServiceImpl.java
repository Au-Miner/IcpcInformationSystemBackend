package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.*;
import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.ApproveCompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveTeamInfo;
import com.IcpcInformationSystemBackend.model.request.ApproveUserInfo;
import com.IcpcInformationSystemBackend.model.response.*;
import com.IcpcInformationSystemBackend.service.ApproveService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.CommonTool;
import com.IcpcInformationSystemBackend.tools.FileTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ApproveServiceImpl implements ApproveService {
    @Resource
    private SchoolDoMapper schoolDoMapper;

    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private AuthTool authTool;

    @Resource
    private CommonTool commonTool;

    @Resource
    private CompetitionDoMapper competitionDoMapper;

    @Resource
    private TeamDoMapper teamDoMapper;

    @Resource
    private FileTool fileTool;

    @Resource
    private TeamScoreDoMapper teamScoreDoMapper;

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
        // if (userDos.get(0).getUserState() != 1)
        //     return ResultTool.error(EmAllException.USER_DONT_NEED_APPROVE);
        if (!Objects.equals(userDos.get(0).getSchoolId(), approveSchoolInfo.getSchoolId()))
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (userDos.get(0).getIdentity() != 4)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(approveSchoolInfo.getSchoolId());
        List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
        if (schoolDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_SCHOOL);
        // if (schoolDos.get(0).getSchoolState() != 1)
        //     return ResultTool.error(EmAllException.SCHOOL_DONT_NEED_APPROVE);
        if (approveSchoolInfo.getApproveResult() != 2 && approveSchoolInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        schoolDos.get(0).setSchoolState(approveSchoolInfo.getApproveResult());
        userDos.get(0).setUserState(approveSchoolInfo.getApproveResult());
        if (schoolDoMapper.updateByPrimaryKeySelective(schoolDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (userDoMapper.updateByPrimaryKeySelective(userDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result approveCoachRegister(ApproveUserInfo approveUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(authTool.getUserId());
        List<UserDo> userDos1 = userDoMapper.selectByExample(userDoExample);
        if (userDos1.isEmpty())
            return ResultTool.error(EmAllException.TOKEN_ERROR);
        String schoolId = userDos1.get(0).getSchoolId();
        userDoExample.clear();
        userDoExample.createCriteria().andUserEmailEqualTo(approveUserInfo.getUserEmail());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        // if (userDos.get(0).getUserState() != 1)
        //     return ResultTool.error(EmAllException.USER_DONT_NEED_APPROVE);
        if (userDos.get(0).getIdentity() != 2)
            return ResultTool.error(EmAllException.USER_IDENTITY_ERROR);
        if (!Objects.equals(userDos.get(0).getSchoolId(), schoolId))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        if (approveUserInfo.getApproveResult() != 2 && approveUserInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        userDos.get(0).setUserState(approveUserInfo.getApproveResult());
        if (userDoMapper.updateByPrimaryKeySelective(userDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result approveStudentRegister(ApproveUserInfo approveUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(authTool.getUserId());
        List<UserDo> userDos1 = userDoMapper.selectByExample(userDoExample);
        if (userDos1.isEmpty())
            return ResultTool.error(EmAllException.TOKEN_ERROR);
        String schoolId = userDos1.get(0).getSchoolId();
        userDoExample.clear();
        userDoExample.createCriteria().andUserEmailEqualTo(approveUserInfo.getUserEmail());
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        if (userDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        // if (userDos.get(0).getUserState() != 1)
        //     return ResultTool.error(EmAllException.USER_DONT_NEED_APPROVE);
        if (userDos.get(0).getIdentity() != 1)
            return ResultTool.error(EmAllException.USER_IDENTITY_ERROR);
        if (!Objects.equals(userDos.get(0).getSchoolId(), schoolId))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        if (approveUserInfo.getApproveResult() != 2 && approveUserInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        userDos.get(0).setUserState(approveUserInfo.getApproveResult());
        if (userDoMapper.updateByPrimaryKeySelective(userDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result approveCompetitionRegister(ApproveCompetitionInfo approveCompetitionInfo) {
        CompetitionDoExample competitionDoExample = new CompetitionDoExample();
        competitionDoExample.createCriteria().andCompetitionIdEqualTo(approveCompetitionInfo.getCompetitionId());
        List<CompetitionDo> competitionDos = competitionDoMapper.selectByExample(competitionDoExample);
        if (competitionDos.isEmpty())
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        // if (competitionDos.get(0).getCompetitionState() != 1)
        //     return ResultTool.error(EmAllException.COMPETITION_DONT_NEED_APPROVE);
        if (approveCompetitionInfo.getApproveResult() != 2 && approveCompetitionInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        BeanUtils.copyProperties(approveCompetitionInfo, competitionDos.get(0));
        competitionDos.get(0).setCompetitionState(approveCompetitionInfo.getApproveResult());
        if (competitionDoMapper.updateByPrimaryKeySelective(competitionDos.get(0)) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result coachGetTeamInfoByCompetitionId(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        TeamDoExample teamDoExample = new TeamDoExample();
        TeamDoExample.Criteria criteria1 = teamDoExample.createCriteria();
        criteria1.andCompetitionIdEqualTo(competitionId).andCoach1EmailEqualTo(authTool.getUserId());
        TeamDoExample.Criteria criteria2 = teamDoExample.createCriteria();
        criteria2.andCompetitionIdEqualTo(competitionId).andCoach2EmailEqualTo(authTool.getUserId());
        teamDoExample.or(criteria2);
        List<TeamDo> teamDos = teamDoMapper.selectByExample(teamDoExample);
        ArrayList<TeamInfoResponse> res = new ArrayList<>();
        for (TeamDo teamDo : teamDos) {
            TeamInfoResponse teamInfoResponse = new TeamInfoResponse();
            BeanUtils.copyProperties(teamDo, teamInfoResponse);
            teamInfoResponse.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
            teamInfoResponse.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
            teamInfoResponse.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
            teamInfoResponse.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
            teamInfoResponse.setCoach2chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach2Email()));

            TeamScoreDo teamScoreDo = commonTool.getTeamScoreByCompetitionIdAndTeamId(competitionId, teamDo.getTeamId());
            if (teamScoreDo != null)
                BeanUtils.copyProperties(teamScoreDo, teamInfoResponse);
            res.add(teamInfoResponse);
        }
        return ResultTool.success(res);
    }

    @Override
    public Result coachApproveTeamInfoByTeamKey(ApproveTeamInfo approveTeamInfo) {
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(approveTeamInfo.getCompetitionId(), approveTeamInfo.getTeamId());
        if (teamDo == null)
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        if (!Objects.equals(teamDo.getCoach1Email(), authTool.getUserId()) && !Objects.equals(teamDo.getCoach2Email(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        if (approveTeamInfo.getApproveResult() != 2 && approveTeamInfo.getApproveResult() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (teamDo.getTeamState() != 1 && teamDo.getTeamState() != 2 && teamDo.getTeamState() != 3)
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        teamDo.setReason(approveTeamInfo.getApproveReason());
        teamDo.setTeamState(approveTeamInfo.getApproveResult());
        if (teamDoMapper.updateByPrimaryKeySelective(teamDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result competitionChairmanGetTeamInfoByCompetitionId(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(competitionId, authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        List<TeamDo> teamDos = commonTool.getTeamsByCompetitionId(competitionId);
        ArrayList<TeamInfoResponse> res = new ArrayList<>();
        for (TeamDo teamDo : teamDos) {
            TeamInfoResponse teamInfoResponse = new TeamInfoResponse();
            BeanUtils.copyProperties(teamDo, teamInfoResponse);
            teamInfoResponse.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
            teamInfoResponse.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
            teamInfoResponse.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
            teamInfoResponse.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
            teamInfoResponse.setCoach2chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach2Email()));
            TeamScoreDo teamScoreDo = commonTool.getTeamScoreByCompetitionIdAndTeamId(competitionId, teamDo.getTeamId());
            if (teamScoreDo != null)
                BeanUtils.copyProperties(teamScoreDo, teamInfoResponse);
            res.add(teamInfoResponse);
        }
        return ResultTool.success(res);
    }

    @Override
    public Result competitionChairmanApproveTeamInfoByTeamKey(ApproveTeamInfo approveTeamInfo) {
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(approveTeamInfo.getCompetitionId(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(approveTeamInfo.getCompetitionId(), approveTeamInfo.getTeamId());
        if (teamDo == null)
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        if (approveTeamInfo.getApproveResult() != 4 && approveTeamInfo.getApproveResult() != 5)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (teamDo.getTeamState() != 2 && teamDo.getTeamState() != 4 && teamDo.getTeamState() != 5)
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        teamDo.setReason(approveTeamInfo.getApproveReason());
        teamDo.setTeamState(approveTeamInfo.getApproveResult());
        if (teamDoMapper.updateByPrimaryKeySelective(teamDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result getStudentInfo() {
        String schoolId = commonTool.getSchoolIdByUserEmail(authTool.getUserId());
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andSchoolIdEqualTo(schoolId);
        List<UserDo> userDos = userDoMapper.selectByExample(userDoExample);
        ArrayList<UserInfoResponse> res = new ArrayList<>();
        for (UserDo userDo : userDos) {
            if (userDo.getUserState() == 2 && userDo.getIdentity() == 1) {
                UserInfoResponse userInfoResponse = new UserInfoResponse();
                BeanUtils.copyProperties(userDo, userInfoResponse);
                res.add(userInfoResponse);
            }
        }
        return ResultTool.success(res);
    }

    @Override
    public Result deleteSchoolImg(String schoolId) {
        SchoolDo schoolDo = commonTool.getSchoolDoBySchoolId(schoolId);
        try {
            fileTool.deleteLocalFile(schoolDo.getSchoolImg());
        } catch (AllException e) {
            return ResultTool.error(EmAllException.NO_SUCH_FILE);
        }
        return ResultTool.success();
    }
}
