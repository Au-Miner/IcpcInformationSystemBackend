package com.IcpcInformationSystemBackend.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CompetitionInfoResponse {
    private String competitionId;
    private String competitionChiName;
    private String competitionEngName;
    private String schoolId;
    private String builderEmail;
    private String heldPosition;
    private @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") Date registrationStartTime;
    private @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") Date registrationEndTime;
    private @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") Date payStartTime;
    private @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") Date payEndTime;
    private @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") Date competitionStartTime;
    private @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") Date competitionEndTime;
    private String duration;
    private String competitionIntroduction;
    private Integer competitionState;
    private String approveReason;

    private Integer identity;
    private String chiName;
    private String engName;
    private String telephone;
}
