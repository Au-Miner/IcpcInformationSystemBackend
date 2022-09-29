package com.IcpcInformationSystemBackend.model.response;

import lombok.Data;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;
}
