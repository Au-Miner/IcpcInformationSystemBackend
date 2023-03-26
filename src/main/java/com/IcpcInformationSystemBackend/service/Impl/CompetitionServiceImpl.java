package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.CompetitionDoMapper;
import com.IcpcInformationSystemBackend.dao.TeamDoMapper;
import com.IcpcInformationSystemBackend.dao.TeamScoreDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.RegisterCompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.UpdateTeamScoresInfo;
import com.IcpcInformationSystemBackend.model.response.CompetitionEntryListResponse;
import com.IcpcInformationSystemBackend.model.response.CompetitionInfoResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.CompetitionService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.CommonTool;
import com.IcpcInformationSystemBackend.tools.FileTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static com.IcpcInformationSystemBackend.model.ConstantRepository.TEAM_SCORES_SHEET_NAME;

@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Resource
    private CompetitionDoMapper competitionDoMapper;

    @Resource
    private UserDoMapper userDoMapper;

    @Resource
    private AuthTool authTool;

    @Resource
    private CommonTool commonTool;

    @Resource
    private FileTool fileTool;

    @Resource
    private TeamScoreDoMapper teamScoreDoMapper;

    @Resource
    private TeamDoMapper teamDoMapper;

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
        if (registerCompetitionInfo.getIfIcpcRegionalCompetition() == 0)
            competitionDo.setIcpcRegionalCompetitionYear("");
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
            competitionInfoResponse.setIfIcpcRegionalCompetition(competitionDo.getIfIcpcRegionalCompetition());
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
            competitionInfoResponse.setIfIcpcRegionalCompetition(competitionDo.getIfIcpcRegionalCompetition());
            res.add(competitionInfoResponse);
        }
        return ResultTool.success(res);
    }

    @Override
    public Result getCompetitionEntryList(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionStateIfPass(competitionId))
            return ResultTool.error(EmAllException.COMPETITION_STATE_ERROR);
        List<TeamDo> teamDos = commonTool.getTeamsByCompetitionId(competitionId);
        ArrayList<CompetitionEntryListResponse> res = new ArrayList<>();
        for (TeamDo teamDo : teamDos) {
            CompetitionEntryListResponse tmp = new CompetitionEntryListResponse();
            BeanUtils.copyProperties(teamDo, tmp);
            tmp.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
            tmp.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
            tmp.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
            tmp.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
            tmp.setCoach2chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach2Email()));
            String type = "正式队伍";
            if (teamDo.getType() == 2)
                type = "打星队伍";
            else if (teamDo.getType() == 1)
                type = "外卡队伍";
            tmp.setType(type);
            res.add(tmp);
        }
        return ResultTool.success(res);
    }

    @Override
    public ArrayList<CompetitionEntryListResponse> getCompetitionEntryList2(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return null;
        if (!commonTool.judgeCompetitionStateIfPass(competitionId))
            return null;
        List<TeamDo> teamDos = commonTool.getTeamsByCompetitionId(competitionId);
        ArrayList<CompetitionEntryListResponse> res = new ArrayList<>();
        for (TeamDo teamDo : teamDos) {
            CompetitionEntryListResponse tmp = new CompetitionEntryListResponse();
            BeanUtils.copyProperties(teamDo, tmp);
            tmp.setMember1chiName(commonTool.getChiNameByUserEmail(teamDo.getMember1Email()));
            tmp.setMember2chiName(commonTool.getChiNameByUserEmail(teamDo.getMember2Email()));
            tmp.setMember3chiName(commonTool.getChiNameByUserEmail(teamDo.getMember3Email()));
            tmp.setCoach1chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach1Email()));
            tmp.setCoach2chiName(commonTool.getChiNameByUserEmail(teamDo.getCoach2Email()));
            String type = "正式队伍";
            if (teamDo.getType() == 2)
                type = "打星队伍";
            else if (teamDo.getType() == 1)
                type = "外卡队伍";
            tmp.setType(type);
            res.add(tmp);
        }
        return res;
    }

    @Override
    public Result updateTeamScores(UpdateTeamScoresInfo updateTeamScoresInfo) {
        if (!commonTool.judgeCompetitionIdIfExists(updateTeamScoresInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionStateIfPass(updateTeamScoresInfo.getCompetitionId()))
            return ResultTool.error(EmAllException.COMPETITION_STATE_ERROR);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(updateTeamScoresInfo.getCompetitionId(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        XSSFSheet sheet = fileTool.getExcelSheet(updateTeamScoresInfo.getTeamScoresAddress(), TEAM_SCORES_SHEET_NAME);
        if (sheet == null)
            return ResultTool.error(EmAllException.NO_SUCH_FILE);
        switch (judgeTeamScoresFormatIfRight(sheet, updateTeamScoresInfo.getCompetitionId())) {
            case 0:
                return ResultTool.error(EmAllException.FILE_FORMAT_ERROR);
            case 1:
                return ResultTool.error(EmAllException.NO_SUCH_TEAM);
            default:
                break;
        }
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i = 1; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            String ss = row.getCell(0).toString();
            int pos = ss.length();
            for (int j = 0; j < ss.length(); j++) {
                if (ss.charAt(j) == '.') {
                    pos = j;
                    break;
                }
            }
            ss = ss.substring(0, pos);
            String rnk = ss;
            String teamId = row.getCell(1).toString();
            String chiMedal = row.getCell(2).toString();
            String engMedal = row.getCell(3).toString();
            TeamScoreDo teamScoreDo = new TeamScoreDo();
            teamScoreDo.setTeamId(teamId);
            teamScoreDo.setRnk(rnk);
            teamScoreDo.setChiMedal(chiMedal);
            teamScoreDo.setEngMedal(engMedal);
            teamScoreDo.setCompetitionId(updateTeamScoresInfo.getCompetitionId());
            TeamScoreDoExample teamScoreDoExample = new TeamScoreDoExample();
            teamScoreDoExample.createCriteria().andCompetitionIdEqualTo(updateTeamScoresInfo.getCompetitionId()).andTeamIdEqualTo(teamId);
            if (teamScoreDoMapper.countByExample(teamScoreDoExample) == 0) {
                if (teamScoreDoMapper.insertSelective(teamScoreDo) == 0)
                    return ResultTool.error(EmAllException.DATABASE_ERR);
            }
            else if (teamScoreDoMapper.updateByPrimaryKeySelective(teamScoreDo) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
        }
        return ResultTool.success();
    }

    public int judgeTeamScoresFormatIfRight(XSSFSheet sheet, String competitionId) {
        int rows = sheet.getPhysicalNumberOfRows();
        if (rows <= 1)
            return 0;
        for(int i = 1; i < rows; i++) {
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            if (columns != 4)
                return 0;
            String cell1 = row.getCell(0).toString();
            String cell2 = row.getCell(1).toString();
            int mark = 0;
            for (int j = 0; j < cell1.length(); j++) {
                if (mark == 0) {
                    if (cell1.charAt(j) == '.')
                        mark = 1;
                    else if (cell1.charAt(j) < '0' || cell1.charAt(j) > '9')
                        return 0;
                }
                else if (cell1.charAt(j) != '0')
                    return 0;
            }
            if (!commonTool.judgeTeamIfExists(competitionId, cell2))
                return 1;
        }
        return 2;
    }

    @Override
    public Result clearCompetitionScore(String competitionId) {
        TeamScoreDo teamScoreDo = new TeamScoreDo();
        teamScoreDo.setRnk("");
        teamScoreDo.setChiMedal("");
        teamScoreDo.setEngMedal("");
        TeamScoreDoExample teamScoreDoExample = new TeamScoreDoExample();
        teamScoreDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        if (!teamScoreDoMapper.selectByExample(teamScoreDoExample).isEmpty() && teamScoreDoMapper.updateByExampleSelective(teamScoreDo, teamScoreDoExample) == 0) {
            return ResultTool.error(EmAllException.DATABASE_ERR);
        }
        return ResultTool.success();
    }

    @Override
    public Result deleteCompetition(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (competitionDoMapper.deleteByPrimaryKey(competitionId) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        if (teamDoMapper.deleteByExample(teamDoExample) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        TeamScoreDoExample teamScoreDoExample = new TeamScoreDoExample();
        teamScoreDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<TeamScoreDo> teamScoreDos = teamScoreDoMapper.selectByExample(teamScoreDoExample);
        for (TeamScoreDo teamScoreDo : teamScoreDos) {
            if (!Objects.equals(teamScoreDo.getPhotos(), "")) {
                try {
                    fileTool.deleteLocalFile(teamScoreDo.getPhotos());
                } catch (AllException ignored) {}
            }
        }
        if (teamScoreDoMapper.deleteByExample(teamScoreDoExample) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result checkTeamCompetitionType(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionTypeIfTeamCompetition(competitionId))
            return ResultTool.error(EmAllException.COMPETITION_TYPE_ERROR);
        return ResultTool.success();
    }
}
