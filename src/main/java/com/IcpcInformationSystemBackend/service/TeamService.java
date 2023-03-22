package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.CompetitionAdmissionTicketResponse;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.TeamInfoResponse;
import com.IcpcInformationSystemBackend.model.response.TeamScoreInfoResponse;

import javax.servlet.http.HttpServletResponse;

public interface TeamService {
    Result studentSignUp4Competition(RegisterTeamInfo registerTeamInfo, boolean ifFirstCreateTeam);

    Result coachSignUp4Competition(RegisterTeamInfo registerTeamInfo, boolean ifFirstCreateTeam);

    Result studentSignUp4PersonalCompetition(String competitionId, Integer type, Integer needTeamCertificate);

    Result getOwnTeamInfo(String competitionId);

    Result deleteTeamInfo(String competitionId, String teamId);

    String generateTeamId(String competitionId);

    Result getCompetitionCertificateInfo(String competitionId, String teamId);

    TeamScoreInfoResponse getCompetitionCertificateInfo2 (String competitionId, String teamId);

    Result getCompetitionAdmissionTicket(String competitionId, String teamId);

    CompetitionAdmissionTicketResponse getCompetitionAdmissionTicket2(String competitionId, String teamId);
}
