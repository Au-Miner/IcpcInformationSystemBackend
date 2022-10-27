package com.IcpcInformationSystemBackend.model.response;

import lombok.Data;

@Data
public class CompetitionAdmissionTicketResponse {
    private String competitionId;
    private String competitionName;
    private String teamId;
    private String chiTeamName;
    private String engTeamName;
    private String schoolName;
    private String member1chiName;
    private String member2chiName;
    private String member3chiName;
    private String coach1chiName;
    private String competitionTime;
    private String durationTime;
    private String type;
    private String competitionPosition;
}
