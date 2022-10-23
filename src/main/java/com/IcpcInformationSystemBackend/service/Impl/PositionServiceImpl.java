package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.PositionDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.PositionDoExample;
import com.IcpcInformationSystemBackend.model.entity.PositionDo;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
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
        PositionDo positionDo = new PositionDo();
        BeanUtils.copyProperties(positionInfo, positionDo);
        if (positionDoMapper.updateByPrimaryKeySelective(positionDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }
}
