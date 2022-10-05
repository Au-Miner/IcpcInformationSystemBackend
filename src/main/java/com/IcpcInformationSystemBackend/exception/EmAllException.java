package com.IcpcInformationSystemBackend.exception;

/*
 * @Description:
 */
public enum EmAllException implements CommonError{
    EMAIL_SEND_ERROR(403, "邮箱验证码获取失败"),
    SCHOOL_HAVE_REGISTERED(403, "学校已注册"),
    SCHOOL_HAVENOT_REGISTERED(405,"学校未注册"),
    EMAIL_HAVE_REGISTERED(403, "邮箱已注册"),
    EMAIL_CODE_ERROR(403, "邮箱验证码错误"),
    EMAIL_CODE_OVERTIME(403, "邮箱验证码超时"),
    NO_SUCH_USER(403, "查无此人"),
    NO_SUCH_SCHOOL(403, "查无此校"),
    PASSWD_ERROR(403, "密码错误"),
    USER_IDENTITY_ERROR(403, "用户身份错误"),
    BAD_FILE_TYPE(403, "文件上传类型错误"),
    FILE_EMPTY(403, "上传文件为空"),
    DATABASE_ERR(405,"数据库错误"),
    UNKNOWN_ERROR(500,"未知错误"),


    // NO_LOGIN_AUTHORIZATION(403, "没有登录权限"),
    // NO_SUCH_USER(403, "用户不存在"),
    // MULTI_USER(403,"重复的用户"),
    // NO_SUCH_GROUP(403, "小组不存在"),
    // NO_SUCH_PLAN(403, "小组任务不存在"),
    // USER_HAVEN_JOINED_GROUP(403, "用户已经加入小组"),
    // USER_NOT_JOINED_GROUP(403,"用户未曾加入该小组"),
    BAD_REQUEST(400, "请求参数格式有误"),
    TOKEN_ERROR(403,"TOKEN错误"),
    // NOT_AUTHORIZED(403,"无权限"),
    // USER_IN_BLACK_LIST(406,"用户被封"),
    // LEADER_CANT_EXIT_GROUP(405,"组长无法退出小组")
    ;

    // 错误码
    private Integer code;

    // 错误信息
    private String msg;

    EmAllException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getErrCode() {
        return this.code;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.msg = errMsg;
        return this;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
