package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.CompetitionDoMapper;
import com.IcpcInformationSystemBackend.dao.TeamDoMapper;
import com.IcpcInformationSystemBackend.dao.UserCompetitionDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.CompetitionAdmissionTicketResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.TeamInfoResponse;
import com.IcpcInformationSystemBackend.model.response.TeamScoreInfoResponse;
import com.IcpcInformationSystemBackend.service.TeamService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.EmailTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import com.IcpcInformationSystemBackend.tools.CommonTool;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    @Resource
    private TeamDoMapper teamDoMapper;

    @Resource
    private UserCompetitionDoMapper userCompetitionDoMapper;

    @Resource
    private AuthTool authTool;

    @Resource
    private CommonTool commonTool;

    @Resource
    private EmailTool emailTool;

    @Override
    public Result studentSignUp4Competition(RegisterTeamInfo registerTeamInfo, boolean ifFirstCreateTeam) {
        if (ifFirstCreateTeam) {
            if (!Objects.equals(registerTeamInfo.getTeamId(), ""))
                return ResultTool.error(EmAllException.BAD_REQUEST);
        }
        else {
            if (Objects.equals(registerTeamInfo.getTeamId(), ""))
                return ResultTool.error(EmAllException.BAD_REQUEST);
        }
        if (!Objects.equals(authTool.getUserId(), registerTeamInfo.getMember1Email()) && !Objects.equals(authTool.getUserId(), registerTeamInfo.getMember2Email()) && !Objects.equals(authTool.getUserId(), registerTeamInfo.getMember3Email()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        if (!commonTool.judgeSchoolIdIfExists(registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.NO_SUCH_SCHOOL);
        if (!commonTool.judgeSchoolStateIfPass(registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.SCHOOL_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeCompetitionIdIfExists(registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionStateIfPass(registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.COMPETITION_STATE_ERROR);
        switch (commonTool.judgeTeamRegisterIfRightAboutCompetitionTime(registerTeamInfo.getCompetitionId())) {
            case -1:
                return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
            case 1:
                return ResultTool.error(EmAllException.COMPETITION_NOT_START);
            case 2:
                return ResultTool.error(EmAllException.COMPETITION_HAS_END);
            default:
                break;
        }
        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getMember1Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getMember2Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getMember3Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getCoach1Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getMember1Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getMember2Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getMember3Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getCoach1Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);

        if (commonTool.judgeUserIfHasSignUp4Competition(registerTeamInfo.getMember1Email(), registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.USER_HAS_SIGN_UP_4_COMPETITION);
        if (commonTool.judgeUserIfHasSignUp4Competition(registerTeamInfo.getMember2Email(), registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.USER_HAS_SIGN_UP_4_COMPETITION);
        if (commonTool.judgeUserIfHasSignUp4Competition(registerTeamInfo.getMember3Email(), registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.USER_HAS_SIGN_UP_4_COMPETITION);
        if (!registerTeamInfo.getCoach2Email().isEmpty()) {
            if (!emailTool.judgeEmailFormatIfRight(registerTeamInfo.getCoach2Email()))
                return ResultTool.error(EmAllException.EMAIL_FORMAT_ERROR);
            if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getCoach2Email()))
                return ResultTool.error(EmAllException.NO_SUCH_USER);
            if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getCoach2Email()))
                return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        }
        String schoolId1 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getMember1Email());
        String schoolId2 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getMember2Email());
        String schoolId3 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getMember3Email());
        String schoolId4 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getCoach1Email());
        String schoolId5 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getCoach2Email());
        if (!Objects.equals(schoolId1, registerTeamInfo.getSchoolId()) || !Objects.equals(schoolId2, registerTeamInfo.getSchoolId()) || !Objects.equals(schoolId3, registerTeamInfo.getSchoolId()) || !Objects.equals(schoolId4, registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.TEAM_USERS_NOT_SAME_SCHOOL);
        if (!Objects.equals(schoolId5, "") && !Objects.equals(schoolId5, registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.TEAM_USERS_NOT_SAME_SCHOOL);
        if (registerTeamInfo.getType() != 1 && registerTeamInfo.getType() != 2 && registerTeamInfo.getType() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (registerTeamInfo.getNeedTeamCertificate() != 1 && registerTeamInfo.getNeedTeamCertificate() != 2)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        String teamId;
        if (ifFirstCreateTeam)
            teamId = generateTeamId(registerTeamInfo.getCompetitionId());
        else
            teamId = registerTeamInfo.getTeamId();
        TeamDo teamDo = new TeamDo();
        BeanUtils.copyProperties(registerTeamInfo, teamDo);
        teamDo.setTeamId(teamId);
        teamDo.setTeamState(1);
        if (teamDoMapper.insertSelective(teamDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        UserCompetitionDo userCompetitionDo = new UserCompetitionDo();
        userCompetitionDo.setCompetitionId(registerTeamInfo.getCompetitionId());
        userCompetitionDo.setTeamId(teamId);
        userCompetitionDo.setStudentEmail(registerTeamInfo.getMember1Email());
        if (userCompetitionDoMapper.insertSelective(userCompetitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        userCompetitionDo.setStudentEmail(registerTeamInfo.getMember2Email());
        if (userCompetitionDoMapper.insertSelective(userCompetitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        userCompetitionDo.setStudentEmail(registerTeamInfo.getMember3Email());
        if (userCompetitionDoMapper.insertSelective(userCompetitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public String generateTeamId(String competitionId) {
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
            TeamDoExample teamDoExample = new TeamDoExample();
            teamDoExample.createCriteria().andCompetitionIdEqualTo(competitionId).andTeamIdEqualTo(String.valueOf(tmp));
            if (teamDoMapper.countByExample(teamDoExample) == 0)
                return String.valueOf(tmp);
        }
    }

    @Override
    public Result getOwnTeamInfo(String competitionId) {
        if (Objects.equals(competitionId, ""))
            return ResultTool.error(EmAllException.BAD_REQUEST);
        String teamId = commonTool.getTeamIdByCompetitionIdAndUserEmail(competitionId, authTool.getUserId());
        if (Objects.equals(teamId, ""))
            return ResultTool.error(EmAllException.USER_NOT_SIGN_UP_4_COMPETITION);
        TeamInfoResponse teamInfoResponse = new TeamInfoResponse();
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId);
        if (teamDo == null)
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        BeanUtils.copyProperties(teamDo, teamInfoResponse);
        teamInfoResponse.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
        teamInfoResponse.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
        teamInfoResponse.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
        teamInfoResponse.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
        teamInfoResponse.setCoach2chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach2Email()));
        ArrayList<TeamInfoResponse> res = new ArrayList<>();
        res.add(teamInfoResponse);
        return ResultTool.success(res);
    }

    @Override
    public Result deleteTeamInfo(String competitionId, String teamId) {
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId);
        if (teamDo == null)
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        if (!Objects.equals(teamDo.getMember1Email(), authTool.getUserId()) && !Objects.equals(teamDo.getMember2Email(), authTool.getUserId())
        && !Objects.equals(teamDo.getMember3Email(), authTool.getUserId()) && !Objects.equals(teamDo.getCoach1Email(), authTool.getUserId())
        && !Objects.equals(teamDo.getCoach2Email(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        UserCompetitionDoExample userCompetitionDoExample = new UserCompetitionDoExample();
        userCompetitionDoExample.createCriteria().andTeamIdEqualTo(teamId).andCompetitionIdEqualTo(competitionId);
        if (userCompetitionDoMapper.deleteByExample(userCompetitionDoExample) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andTeamIdEqualTo(teamId).andCompetitionIdEqualTo(competitionId);
        if (teamDoMapper.deleteByExample(teamDoExample) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result coachSignUp4Competition(RegisterTeamInfo registerTeamInfo, boolean ifFirstCreateTeam) {
        if (ifFirstCreateTeam) {
            if (!Objects.equals(registerTeamInfo.getTeamId(), ""))
                return ResultTool.error(EmAllException.BAD_REQUEST);
        } else {
            if (Objects.equals(registerTeamInfo.getTeamId(), ""))
                return ResultTool.error(EmAllException.BAD_REQUEST);
        }
        if (!Objects.equals(authTool.getUserId(), registerTeamInfo.getCoach1Email()) && !Objects.equals(authTool.getUserId(), registerTeamInfo.getCoach2Email()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        if (!commonTool.judgeSchoolIdIfExists(registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.NO_SUCH_SCHOOL);
        if (!commonTool.judgeSchoolStateIfPass(registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.SCHOOL_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeCompetitionIdIfExists(registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionStateIfPass(registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.COMPETITION_STATE_ERROR);
        switch (commonTool.judgeTeamRegisterIfRightAboutCompetitionTime(registerTeamInfo.getCompetitionId())) {
            case -1:
                return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
            case 1:
                return ResultTool.error(EmAllException.COMPETITION_NOT_START);
            case 2:
                return ResultTool.error(EmAllException.COMPETITION_HAS_END);
            default:
                break;
        }

        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getMember1Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getMember2Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getMember3Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getCoach1Email()))
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getMember1Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getMember2Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getMember3Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getCoach1Email()))
            return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);

        if (commonTool.judgeUserIfHasSignUp4Competition(registerTeamInfo.getMember1Email(), registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.USER_HAS_SIGN_UP_4_COMPETITION);
        if (commonTool.judgeUserIfHasSignUp4Competition(registerTeamInfo.getMember2Email(), registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.USER_HAS_SIGN_UP_4_COMPETITION);
        if (commonTool.judgeUserIfHasSignUp4Competition(registerTeamInfo.getMember3Email(), registerTeamInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.USER_HAS_SIGN_UP_4_COMPETITION);
        if (!registerTeamInfo.getCoach2Email().isEmpty()) {
            if (!emailTool.judgeEmailFormatIfRight(registerTeamInfo.getCoach2Email()))
                return ResultTool.error(EmAllException.EMAIL_FORMAT_ERROR);
            if (!commonTool.judgeUserEmailIfExists(registerTeamInfo.getCoach2Email()))
                return ResultTool.error(EmAllException.NO_SUCH_USER);
            if (!commonTool.judgeUserStateIfRight(registerTeamInfo.getCoach2Email()))
                return ResultTool.error(EmAllException.USER_DONT_APPROVE_SUCCESS);
        }
        String schoolId1 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getMember1Email());
        String schoolId2 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getMember2Email());
        String schoolId3 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getMember3Email());
        String schoolId4 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getCoach1Email());
        String schoolId5 = commonTool.getSchoolIdByUserEmail(registerTeamInfo.getCoach2Email());
        if (!Objects.equals(schoolId1, registerTeamInfo.getSchoolId()) || !Objects.equals(schoolId2, registerTeamInfo.getSchoolId()) || !Objects.equals(schoolId3, registerTeamInfo.getSchoolId()) || !Objects.equals(schoolId4, registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.TEAM_USERS_NOT_SAME_SCHOOL);
        if (!Objects.equals(schoolId5, "") && !Objects.equals(schoolId5, registerTeamInfo.getSchoolId()))
            return ResultTool.error(EmAllException.TEAM_USERS_NOT_SAME_SCHOOL);
        if (registerTeamInfo.getType() != 1 && registerTeamInfo.getType() != 2 && registerTeamInfo.getType() != 3)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (registerTeamInfo.getNeedTeamCertificate() != 1 && registerTeamInfo.getNeedTeamCertificate() != 2)
            return ResultTool.error(EmAllException.BAD_REQUEST);
        String teamId;
        if (ifFirstCreateTeam)
            teamId = generateTeamId(registerTeamInfo.getCompetitionId());
        else
            teamId = registerTeamInfo.getTeamId();
        TeamDo teamDo = new TeamDo();
        BeanUtils.copyProperties(registerTeamInfo, teamDo);
        teamDo.setTeamId(teamId);
        teamDo.setTeamState(2);
        if (teamDoMapper.insertSelective(teamDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        UserCompetitionDo userCompetitionDo = new UserCompetitionDo();
        userCompetitionDo.setCompetitionId(registerTeamInfo.getCompetitionId());
        userCompetitionDo.setTeamId(teamId);
        userCompetitionDo.setStudentEmail(registerTeamInfo.getMember1Email());
        if (userCompetitionDoMapper.insertSelective(userCompetitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        userCompetitionDo.setStudentEmail(registerTeamInfo.getMember2Email());
        if (userCompetitionDoMapper.insertSelective(userCompetitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        userCompetitionDo.setStudentEmail(registerTeamInfo.getMember3Email());
        if (userCompetitionDoMapper.insertSelective(userCompetitionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result getCompetitionCertificateInfo(String competitionId, String teamId) {
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId);
        TeamScoreDo teamScoreDo = commonTool.getTeamScoreByCompetitionIdAndTeamId(competitionId, teamId);
        if (teamDo == null)
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        if (teamDo.getTeamState() != 4)
            return ResultTool.error(EmAllException.TEAM_DONT_APPROVE_SUCCESS);
        if (teamScoreDo == null || teamScoreDo.getRnk() == null)
            return ResultTool.error(EmAllException.TEAM_SCORE_DONT_RELEASE);
        // if (!Objects.equals(teamDo.getMember1Email(), authTool.getUserId())
        //         && !Objects.equals(teamDo.getMember2Email(), authTool.getUserId())
        //         && !Objects.equals(teamDo.getMember3Email(), authTool.getUserId())
        //         && !Objects.equals(teamDo.getCoach1Email(), authTool.getUserId())
        //         && (!Objects.equals(teamDo.getCoach2Email(), "") && !Objects.equals(teamDo.getCoach2Email(), authTool.getUserId()))
        // )
        //     return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        TeamScoreInfoResponse res = new TeamScoreInfoResponse();
        BeanUtils.copyProperties(teamDo, res);
        res.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
        res.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
        res.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
        res.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
        res.setCoach2chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach2Email()));
        res.setMember1engName(commonTool.getEngNameByUserEmail(teamDo.getMember1Email()));
        res.setMember2engName(commonTool.getEngNameByUserEmail(teamDo.getMember2Email()));
        res.setMember3engName(commonTool.getEngNameByUserEmail(teamDo.getMember3Email()));
        res.setCoach1engName(commonTool.getEngNameByUserEmail(teamDo.getCoach1Email()));
        res.setCoach2engName(commonTool.getEngNameByUserEmail(teamDo.getCoach2Email()));
        CompetitionDo competitionDo = commonTool.getCompetitionDoByCompetitionId(competitionId);
        res.setCompetitionChiName(competitionDo.getCompetitionChiName());
        res.setCompetitionEngName(competitionDo.getCompetitionEngName());
        Calendar ca = Calendar.getInstance();
        ca.setTime(competitionDo.getCompetitionStartTime());
        String CompetitionChiTime = ca.get(Calendar.YEAR) + "年" + ca.get(Calendar.MONTH) + "月" + ca.get(Calendar.DAY_OF_MONTH) + "日";
        String CompetitionEngTime = monNumberToEnglish[ca.get(Calendar.MONTH) - 1] + " " + ca.get(Calendar.DAY_OF_MONTH) + "，" + ca.get(Calendar.YEAR);
        res.setCompetitionChiTime(CompetitionChiTime);
        res.setCompetitionEngTime(CompetitionEngTime);
        SchoolDo schoolDo = commonTool.getSchoolDoBySchoolId(teamDo.getSchoolId());
        res.setSchoolImg(schoolDo.getSchoolImg());
        res.setChiSchoolName(schoolDo.getChiSchoolName());
        res.setEngSchoolName(schoolDo.getEngSchoolName());
        BeanUtils.copyProperties(teamScoreDo, res);
        return ResultTool.success(res);
    }

    public TeamScoreInfoResponse getCompetitionCertificateInfo2(String competitionId, String teamId) {
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId);
        TeamScoreDo teamScoreDo = commonTool.getTeamScoreByCompetitionIdAndTeamId(competitionId, teamId);
        if (teamDo == null)
            return null;
        if (teamDo.getTeamState() != 4)
            return null;
        if (teamScoreDo == null || teamScoreDo.getRnk() == null)
            return null;
        TeamScoreInfoResponse res = new TeamScoreInfoResponse();
        BeanUtils.copyProperties(teamDo, res);
        res.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
        res.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
        res.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
        res.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
        res.setCoach2chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach2Email()));
        res.setMember1engName(commonTool.getEngNameByUserEmail(teamDo.getMember1Email()));
        res.setMember2engName(commonTool.getEngNameByUserEmail(teamDo.getMember2Email()));
        res.setMember3engName(commonTool.getEngNameByUserEmail(teamDo.getMember3Email()));
        res.setCoach1engName(commonTool.getEngNameByUserEmail(teamDo.getCoach1Email()));
        res.setCoach2engName(commonTool.getEngNameByUserEmail(teamDo.getCoach2Email()));
        CompetitionDo competitionDo = commonTool.getCompetitionDoByCompetitionId(competitionId);
        res.setCompetitionChiName(competitionDo.getCompetitionChiName());
        res.setCompetitionEngName(competitionDo.getCompetitionEngName());
        Calendar ca = Calendar.getInstance();
        ca.setTime(competitionDo.getCompetitionStartTime());
        String CompetitionChiTime = ca.get(Calendar.YEAR) + "年" + ca.get(Calendar.MONTH) + "月" + ca.get(Calendar.DAY_OF_MONTH) + "日";
        String CompetitionEngTime = monNumberToEnglish[ca.get(Calendar.MONTH) - 1] + " " + ca.get(Calendar.DAY_OF_MONTH) + "，" + ca.get(Calendar.YEAR);
        res.setCompetitionChiTime(CompetitionChiTime);
        res.setCompetitionEngTime(CompetitionEngTime);
        SchoolDo schoolDo = commonTool.getSchoolDoBySchoolId(teamDo.getSchoolId());
        res.setSchoolImg(schoolDo.getSchoolImg());
        res.setChiSchoolName(schoolDo.getChiSchoolName());
        res.setEngSchoolName(schoolDo.getEngSchoolName());
        BeanUtils.copyProperties(teamScoreDo, res);
        return res;
    }

    private final String[] monNumberToEnglish = {"January", "february", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    public Result getCompetitionAdmissionTicket(String competitionId, String teamId) {
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId);
        if (teamDo == null)
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        if (teamDo.getTeamState() != 4)
            return ResultTool.error(EmAllException.TEAM_DONT_APPROVE_SUCCESS);
        if (Objects.equals(teamDo.getCompetitionPosition(), ""))
            return ResultTool.error(EmAllException.TEAM_DONT_ASSIGN_POSITION);
        if (!Objects.equals(teamDo.getMember1Email(), authTool.getUserId()) && !Objects.equals(teamDo.getMember2Email(), authTool.getUserId()) && !Objects.equals(teamDo.getMember3Email(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        CompetitionDo competitionDo = commonTool.getCompetitionDoByCompetitionId(competitionId);
        SchoolDo schoolDo = commonTool.getSchoolDoBySchoolId(teamDo.getSchoolId());
        CompetitionAdmissionTicketResponse tmp = new CompetitionAdmissionTicketResponse();
        BeanUtils.copyProperties(teamDo, tmp);
        tmp.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
        tmp.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
        tmp.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
        tmp.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
        tmp.setCompetitionName(competitionDo.getCompetitionChiName());
        tmp.setSchoolName(schoolDo.getChiSchoolName());

        String competitionTime = "";
        Calendar ca = Calendar.getInstance();
        ca.setTime(competitionDo.getCompetitionStartTime());
        competitionTime += ca.get(Calendar.YEAR);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MONTH);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.DAY_OF_MONTH);
        competitionTime += ' ';
        competitionTime += ca.get(Calendar.HOUR_OF_DAY);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MINUTE);
        competitionTime += " - ";
        ca.setTime(competitionDo.getCompetitionEndTime());
        competitionTime += ca.get(Calendar.YEAR);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MONTH);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.DAY_OF_MONTH);
        competitionTime += ' ';
        competitionTime += ca.get(Calendar.HOUR_OF_DAY);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MINUTE);
        tmp.setCompetitionTime(competitionTime);

        String durationTime = "";
        durationTime += Integer.parseInt(competitionDo.getDuration().substring(0, 2));
        durationTime += "小时";
        int minute = Integer.parseInt(competitionDo.getDuration().substring(3, 5));
        if (minute != 0)
            durationTime += minute + "分钟";
        tmp.setDurationTime(durationTime);
        String type = "正式队伍";
        if (teamDo.getType() == 2)
            type = "打星队伍";
        else if (teamDo.getType() == 1)
            type = "外卡队伍";
        tmp.setType(type);
        return ResultTool.success(tmp);
    }

    @Override
    public CompetitionAdmissionTicketResponse getCompetitionAdmissionTicket2(String competitionId, String teamId) {
        TeamDo teamDo = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId);
        if (teamDo == null)
            return null;
        if (teamDo.getTeamState() != 4)
            return null;
        if (Objects.equals(teamDo.getCompetitionPosition(), ""))
            return null;
        if (!Objects.equals(teamDo.getMember1Email(), authTool.getUserId()) && !Objects.equals(teamDo.getMember2Email(), authTool.getUserId()) && !Objects.equals(teamDo.getMember3Email(), authTool.getUserId()))
            return null;
        CompetitionDo competitionDo = commonTool.getCompetitionDoByCompetitionId(competitionId);
        SchoolDo schoolDo = commonTool.getSchoolDoBySchoolId(teamDo.getSchoolId());
        CompetitionAdmissionTicketResponse tmp = new CompetitionAdmissionTicketResponse();
        BeanUtils.copyProperties(teamDo, tmp);
        tmp.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
        tmp.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
        tmp.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
        tmp.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
        tmp.setCompetitionName(competitionDo.getCompetitionChiName());
        tmp.setSchoolName(schoolDo.getChiSchoolName());

        String competitionTime = "";
        Calendar ca = Calendar.getInstance();
        ca.setTime(competitionDo.getCompetitionStartTime());
        competitionTime += ca.get(Calendar.YEAR);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MONTH);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.DAY_OF_MONTH);
        competitionTime += ' ';
        competitionTime += ca.get(Calendar.HOUR_OF_DAY);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MINUTE);
        competitionTime += " - ";
        ca.setTime(competitionDo.getCompetitionEndTime());
        competitionTime += ca.get(Calendar.YEAR);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MONTH);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.DAY_OF_MONTH);
        competitionTime += ' ';
        competitionTime += ca.get(Calendar.HOUR_OF_DAY);
        competitionTime += '-';
        competitionTime += ca.get(Calendar.MINUTE);
        tmp.setCompetitionTime(competitionTime);

        String durationTime = "";
        durationTime += Integer.parseInt(competitionDo.getDuration().substring(0, 2));
        durationTime += "小时";
        int minute = Integer.parseInt(competitionDo.getDuration().substring(3, 5));
        if (minute != 0)
            durationTime += minute + "分钟";
        tmp.setDurationTime(durationTime);
        String type = "正式队伍";
        if (teamDo.getType() == 2)
            type = "打星队伍";
        else if (teamDo.getType() == 1)
            type = "外卡队伍";
        tmp.setType(type);
        return tmp;
    }
}
