package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.PositionDoMapper;
import com.IcpcInformationSystemBackend.dao.TeamDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.PositionDoExample;
import com.IcpcInformationSystemBackend.model.entity.PositionDo;
import com.IcpcInformationSystemBackend.model.entity.TeamDo;
import com.IcpcInformationSystemBackend.model.entity.TeamDoExample;
import com.IcpcInformationSystemBackend.model.entity.myEntity.MyTeamPositionDo;
import com.IcpcInformationSystemBackend.model.request.PositionInfo;
import com.IcpcInformationSystemBackend.model.response.PositionResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.PositionService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.CommonTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

    @Resource
    private PositionDoMapper positionDoMapper;

    @Resource
    private CommonTool commonTool;

    @Resource
    private AuthTool authTool;

    @Resource
    private TeamDoMapper teamDoMapper;

    @Override
    public Result addPosition(PositionInfo positionInfo) {
        if (!Objects.equals(positionInfo.getPositionId(), ""))
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(positionInfo.getCompetitionId(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        String positionId = generatePositionId();
        PositionDo positionDo = new PositionDo();
        BeanUtils.copyProperties(positionInfo, positionDo);
        positionDo.setPositionId(positionId);
        if (positionDoMapper.insertSelective(positionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public String generatePositionId() {
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
            PositionDoExample positionDoExample = new PositionDoExample();
            positionDoExample.createCriteria().andPositionIdEqualTo(String.valueOf(tmp));
            if (positionDoMapper.countByExample(positionDoExample) == 0)
                return String.valueOf(tmp);
        }
    }

    @Override
    public Result deletePosition(String positionId) {
        PositionDo positionDo = commonTool.getPositionByPositionId(positionId);
        if (positionDo == null)
            return ResultTool.error(EmAllException.NO_SUCH_POSITION);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(positionDo.getCompetitionId(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        if (positionDoMapper.deleteByPrimaryKey(positionId) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result getPositionInfoByCompetitionId(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(competitionId, authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        ArrayList<PositionResponse> res = new ArrayList<>();
        for (PositionDo positionDo : commonTool.getPositionsByCompetitionId(competitionId)) {
            PositionResponse positionResponse = new PositionResponse();
            BeanUtils.copyProperties(positionDo, positionResponse);
            res.add(positionResponse);
        }
        return ResultTool.success(res);
    }

    @Override
    public Result modifyPosition(PositionInfo positionInfo) {
        if (Objects.equals(positionInfo.getPositionId(), ""))
            return ResultTool.error(EmAllException.BAD_REQUEST);
        if (!commonTool.judgePositionIfExists(positionInfo.getPositionId()))
            return ResultTool.error(EmAllException.NO_SUCH_POSITION);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(positionInfo.getCompetitionId(), authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        PositionDo positionDo = new PositionDo();
        BeanUtils.copyProperties(positionInfo, positionDo);
        if (positionDoMapper.updateByPrimaryKeySelective(positionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result assignPositions(String competitionId) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(competitionId, authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        TeamDoExample teamDoExample = new TeamDoExample();
        teamDoExample.createCriteria().andCompetitionIdEqualTo(competitionId).andTeamStateEqualTo(4);
        List<TeamDo> teamDos = teamDoMapper.selectByExample(teamDoExample);
        PositionDoExample positionDoExample = new PositionDoExample();
        positionDoExample.createCriteria().andCompetitionIdEqualTo(competitionId);
        List<PositionDo> positionDos = positionDoMapper.selectByExample(positionDoExample);
        int teamSize = teamDos.size();
        int positionCapacity = 0;
        for (PositionDo positionDo : positionDos)
            positionCapacity += positionDo.getCapacity();
        if (positionCapacity < teamSize)
            return ResultTool.error(EmAllException.POSITION_CAPACITY_NOT_ENOUGH);
        positionCapacity = teamSize;
        ArrayList<MyTeamPositionDo> positions = new ArrayList<>();
        positions.ensureCapacity(teamSize);
        ArrayList<MyTeamPositionDo> myTeamPositionDos = new ArrayList<>();
        myTeamPositionDos.ensureCapacity(teamSize);
        for (int i = 0; i < teamSize; i++) {
            MyTeamPositionDo tmp = new MyTeamPositionDo();
            tmp.setPos(i);
            tmp.setSchoolId(teamDos.get(i).getSchoolId());
            tmp.setTeamId(teamDos.get(i).getTeamId());
            myTeamPositionDos.add(tmp);
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // log.info("positionCapacity: " + positionCapacity);
        while (positionCapacity-- > 0) {
            int wrongTimes = 0;
            int pos;
            while (true) {
                pos = random.nextInt(positionCapacity + 1);
                if (positions.isEmpty() || !Objects.equals(positions.get(positions.size() - 1).getSchoolId(), myTeamPositionDos.get(pos).getSchoolId()))
                    break;
                if (wrongTimes++ == 100) {
                    // log.info("已经重复失败了100次！！！！！！");
                    for (int i = 0; i < positions.size() - 1; i++) {
                        int mark1 = 0, mark2 = 0, mark3 = 0;
                        if (i == 0 || !Objects.equals(positions.get(i - 1).getSchoolId(), myTeamPositionDos.get(pos).getSchoolId()))
                            mark1 = 1;
                        if (!Objects.equals(positions.get(i + 1).getSchoolId(), myTeamPositionDos.get(pos).getSchoolId()))
                            mark2 = 1;
                        if (!Objects.equals(positions.get(positions.size() - 1).getSchoolId(), positions.get(i).getSchoolId()))
                            mark3 = 1;
                        if (mark1 + mark2 + mark3 == 3) {
                            // log.info("和" + i + "成功交换！！！");
                            MyTeamPositionDo tmp = positions.get(i);
                            positions.set(i, myTeamPositionDos.get(pos));
                            myTeamPositionDos.set(pos, tmp);
                            break;
                        }
                    }
                    break;
                }
            }
            positions.add(myTeamPositionDos.get(pos));
            String competitionPosition = findPositionName(positionDos, positions.size());
            // log.info(teamDos.get(myTeamPositionDos.get(pos).getPos()).getTeamId() + "的位置id为：" + competitionPosition);
            teamDos.get(myTeamPositionDos.get(pos).getPos()).setCompetitionPosition(competitionPosition);
            swap(myTeamPositionDos, pos, myTeamPositionDos.size() - 1);
            myTeamPositionDos.remove(myTeamPositionDos.size() - 1);
        }
        for (TeamDo teamDo : teamDos) {
            if (teamDoMapper.updateByPrimaryKeySelective(teamDo) == 0)
                return ResultTool.error(EmAllException.DATABASE_ERR);
        }
        return ResultTool.success();
    }

    void swap(ArrayList<MyTeamPositionDo> ss, int l, int r) {
        MyTeamPositionDo tmp = ss.get(l);
        ss.set(l, ss.get(r));
        ss.set(r, tmp);
    }
    String findPositionName(List<PositionDo> positionDos, int pos) {
        for (PositionDo positionDo : positionDos) {
            if (pos <= positionDo.getCapacity()) {
                String res = positionDo.getPositionName();
                res += '-';
                res += Integer.toString(pos);
                res += '号';
                return res;
            }
            pos -= positionDo.getCapacity();
        }
        return "";
    }

    @Override
    public Result exchangePositions(String competitionId, String teamId1, String teamId2) {
        if (!commonTool.judgeCompetitionIdIfExists(competitionId))
            return ResultTool.error(EmAllException.NO_SUCH_COMPETITION);
        if (!commonTool.judgeCompetitionChairmanIdentityIfRight(competitionId, authTool.getUserId()))
            return ResultTool.error(EmAllException.AUTHORIZATION_ERROR);
        if (!commonTool.judgeCompetitionStateIfPass(competitionId))
            return ResultTool.error(EmAllException.COMPETITION_STATE_ERROR);
        TeamDo teamDo1 = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId1);
        TeamDo teamDo2 = commonTool.getTeamByCompetitionIdAndTeamId(competitionId, teamId2);
        if (teamDo1 == null || teamDo2 == null)
            return ResultTool.error(EmAllException.NO_SUCH_TEAM);
        if (teamDo1.getTeamState() != 4 || teamDo2.getTeamState() != 4)
            return ResultTool.error(EmAllException.TEAM_DONT_APPROVE_SUCCESS);
        if (Objects.equals(teamDo1.getCompetitionPosition(), "") || Objects.equals(teamDo2.getCompetitionPosition(), ""))
            return ResultTool.error(EmAllException.TEAM_DONT_ASSIGN_POSITION);
        String tmp = teamDo1.getCompetitionPosition();
        teamDo1.setCompetitionPosition(teamDo2.getCompetitionPosition());
        teamDo2.setCompetitionPosition(tmp);
        if (teamDoMapper.updateByPrimaryKeySelective(teamDo1) == 0 || teamDoMapper.updateByPrimaryKeySelective(teamDo2) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }
}
