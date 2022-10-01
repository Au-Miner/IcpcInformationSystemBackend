package com.IcpcInformationSystemBackend.model.response;

import lombok.Data;

/*
 * @Description:
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
}
