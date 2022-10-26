package com.IcpcInformationSystemBackend.model.response;

import lombok.Data;

@Data
public class TeamScoreInfoResponse {
    private String teamId;
    private String chiTeamName;
    private String engTeamName;
    private String competitionId;
    private String competitionChiName;
    private String competitionEngName;
    private String competitionChiTime;
    private String competitionEngTime;
    private String schoolImg;
    private String chiSchoolName;
    private String engSchoolName;
    private String member1chiName;
    private String member1engName;
    private String member2chiName;
    private String member2engName;
    private String member3chiName;
    private String member3engName;
    private String coach1chiName;
    private String coach1engName;
    private String coach2chiName;
    private String coach2engName;
    private Integer rnk;
    private String chiMedal;
    private String engMedal;
}
