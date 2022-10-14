package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.CompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.CompetitionModifyInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface CompetitionService {
    Result buildCompetition(CompetitionInfo competitionInfo);

    Result rebuildCompetition(CompetitionInfo competitionInfo);

    Result getAllCompetitionInfo();

    Result getOwnCompetitionInfo();

    Result getAllAcceptCompetitionInfo();

    Result modifyCompetition(CompetitionModifyInfo competitionModifyInfo);

    String generateCompetitionId();
}
