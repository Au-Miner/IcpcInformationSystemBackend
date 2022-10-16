package com.IcpcInformationSystemBackend.service;

import com.IcpcInformationSystemBackend.model.request.RegisterTeamInfo;
import com.IcpcInformationSystemBackend.model.response.Result;

public interface TeamService {
    Result signUp4Competition(RegisterTeamInfo registerTeamInfo);

    String generateTeamId(String competitionId);
}
