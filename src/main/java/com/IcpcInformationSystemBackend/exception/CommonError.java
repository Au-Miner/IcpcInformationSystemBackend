package com.IcpcInformationSystemBackend.exception;

/*
 * @Description: 报错接口
 */
public interface CommonError {
    Integer getErrCode();

    String getMsg();

    CommonError setErrMsg(String errMsg);
}

