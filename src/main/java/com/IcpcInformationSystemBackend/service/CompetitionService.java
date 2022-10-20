package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.RegisterCompetitionInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface CompetitionService {
    Result buildCompetition(RegisterCompetitionInfo registerCompetitionInfo);

    Result rebuildCompetition(RegisterCompetitionInfo registerCompetitionInfo, int state);

    Result getAllCompetitionInfo();

    Result getOwnCompetitionInfo();

    Result getAllAcceptCompetitionInfo();

    String generateCompetitionId();
}
