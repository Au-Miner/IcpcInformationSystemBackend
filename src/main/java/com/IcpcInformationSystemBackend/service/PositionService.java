package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.PositionInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface PositionService {

    Result addPosition(PositionInfo positionInfo);

    Result modifyPosition(PositionInfo positionInfo);

    Result getPositionInfoByCompetitionId(String competitionId);

    Result deletePosition(String positionId);

    String generatePositionId();
}
