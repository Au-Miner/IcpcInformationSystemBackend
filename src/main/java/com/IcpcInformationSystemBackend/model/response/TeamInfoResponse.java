package com.IcpcInformationSystemBackend.model.response;

import lombok.Data;

@Data
public class TeamInfoResponse {
    private String competitionId;
    private String teamId;
    private String chiTeamName;
    private String engTeamName;
    private String schoolId;
    private String member1Email;
    private String member1chiName;
    private String member2Email;
    private String member2chiName;
    private String member3Email;
    private String member3chiName;
    private String coach1Email;
    private String coach1chiName;
    private String coach2Email;
    private String coach2chiName;
    private Integer type;
    private Integer teamState;
    private String reason;
    private Integer needTeamCertificate;
    private String competitionPosition;
    private Integer rnk;
    private String chiMedal;
    private String engMedal;
}
