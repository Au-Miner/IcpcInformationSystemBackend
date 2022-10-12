package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.CompetitionInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface CompetitionService {
    Result buildCompetition(CompetitionInfo competitionInfo);

    String generateCompetitionId();
}
