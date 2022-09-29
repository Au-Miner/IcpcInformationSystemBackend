package com.IcpcInformationSystemBackend.exception;

/*
 * @Author:
 * @Date:2022/1/30
 * @Description: 报错接口
 */
public interface CommonError {
    Integer getErrCode();

    String getMsg();

    CommonError setErrMsg(String errMsg);
}

