package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.ApproveTeamInfo;
import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface TeamService {
    Result signUp4Competition(RegisterTeamInfo registerTeamInfo, boolean ifFirstCreateTeam);

    Result getOwnTeamInfo(String competitionId);

    Result deleteTeamInfo(String competitionId, String teamId);

    String generateTeamId(String competitionId);
}
