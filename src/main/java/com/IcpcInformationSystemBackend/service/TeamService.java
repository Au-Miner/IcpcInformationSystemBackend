package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.TeamScoreInfoResponse;

public interface TeamService {
    Result studentSignUp4Competition(RegisterTeamInfo registerTeamInfo, boolean ifFirstCreateTeam);

    Result coachSignUp4Competition(RegisterTeamInfo registerTeamInfo, boolean ifFirstCreateTeam);

    Result getOwnTeamInfo(String competitionId);

    Result deleteTeamInfo(String competitionId, String teamId);

    String generateTeamId(String competitionId);

    Result getCompetitionAdmissionTicket(String competitionId, String teamId);

    Result getCompetitionCertificateInfo(String competitionId, String teamId);

    TeamScoreInfoResponse getCompetitionCertificateInfo2 (String competitionId, String teamId);
}
