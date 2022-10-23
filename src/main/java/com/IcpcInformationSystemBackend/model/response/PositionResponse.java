package com.IcpcInformationSystemBackend.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PositionResponse {
    private String positionId;
    private String competitionId;
    private String positionName;
    private Integer capacity;
}
