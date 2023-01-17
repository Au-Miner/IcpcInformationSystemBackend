package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.RegisterCompetitionInfo;
import com.IcpcInformationSystemBackend.model.request.UpdateTeamScoresInfo;
import com.IcpcInformationSystemBackend.model.response.CompetitionEntryListResponse;
import com.IcpcInformationSystemBackend.model.response.Result;

import java.util.ArrayList;

public interface CompetitionService {
    Result buildCompetition(RegisterCompetitionInfo registerCompetitionInfo);

    Result rebuildCompetition(RegisterCompetitionInfo registerCompetitionInfo, int state);

    Result getAllCompetitionInfo();

    Result getOwnCompetitionInfo();

    Result getAllAcceptCompetitionInfo();

    String generateCompetitionId();

    Result getCompetitionEntryList(String competitionId);

    ArrayList<CompetitionEntryListResponse> getCompetitionEntryList2(String competitionId);

    Result updateTeamScores(UpdateTeamScoresInfo updateTeamScoresInfo);

    Result clearCompetitionScore(String competitionId);
}
