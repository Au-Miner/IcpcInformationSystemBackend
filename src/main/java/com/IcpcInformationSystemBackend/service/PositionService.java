package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.PositionInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface PositionService {

    Result addPosition(PositionInfo positionInfo);

    String generatePositionId();
}
