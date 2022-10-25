package com.IcpcInformationSystemBackend.model.response;


import lombok.Data;



@Data
public class CompetitionEntryListResponse {
    private String chiTeamName;
    private String engTeamName;
    private String schoolId;
    private String member1chiName;
    private String member2chiName;
    private String member3chiName;
    private String coach1chiName;
    private String coach2chiName;
    private Integer type;
}
