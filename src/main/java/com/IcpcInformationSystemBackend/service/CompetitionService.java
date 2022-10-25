package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.RegisterCompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.UpdateTeamScoresInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface CompetitionService {
    Result buildCompetition(RegisterCompetitionInfo registerCompetitionInfo);

    Result rebuildCompetition(RegisterCompetitionInfo registerCompetitionInfo, int state);

    Result getAllCompetitionInfo();

    Result getOwnCompetitionInfo();

    Result getAllAcceptCompetitionInfo();

    String generateCompetitionId();

    Result getCompetitionEntryList(String competitionId);

    Result getCompetitionAdmissionTicket(String competitionId, String teamId);

    Result updateTeamScores(UpdateTeamScoresInfo updateTeamScoresInfo);
}
