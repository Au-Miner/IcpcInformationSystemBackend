package com.IcpcInformationSystemBackend.exception;

/*
 * @Description:
 */
public enum EmAllException implements CommonError{
    //邮箱相关
    EMAIL_SEND_WRONG(403, "邮箱验证码获取失败"),
    EMAIL_HAVE_REGISTERED(403, "邮箱已注册"),
    EMAIL_CODE_WRONG(403, "邮箱验证码错误"),
    EMAIL_CODE_OVERTIME(403, "邮箱验证码超时"),
    EMAIL_FORMAT_ERROR(403, "邮箱格式错误"),

    //文件相关
    BAD_FILE_TYPE(403, "文件上传类型错误"),
    FILE_EMPTY(403, "上传文件为空"),

    //学校相关
    NO_SUCH_SCHOOL(403, "查无此校"),
    SCHOOL_HAVE_REGISTERED(403, "学校已注册"),
    SCHOOL_NOT_REGISTERED(405,"学校未注册"),
    SCHOOL_DONT_NEED_APPROVE(405,"学校无需审批"),

    //人相关
    NO_SUCH_USER(403, "查无此人"),
    PASSWD_WRONG(403, "密码错误"),
    USER_IDENTITY_ERROR(403, "用户身份错误"),
    USER_DONT_NEED_APPROVE(403, "用户无需审批"),
    USER_DONT_APPROVE_SUCCESS(403, "用户未审批通过"),
    AUTHORIZATION_ERROR(403, "权限错误"),

    //比赛相关
    NO_SUCH_COMPETITION(403,"查无此赛"),
    COMPETITION_DONT_NEED_APPROVE(403,"比赛无需审批"),
    COMPETITION_STATE_ERROR(403, "比赛状态错误"),

    //队伍相关
    NO_SUCH_TEAM(403, "查无此队"),
    TEAM_USERS_NOT_SAME_SCHOOL(403, "队伍成员不属于同一学校"),
    TEAM_DONT_NEED_APPROVE(403, "队伍无需审批"),
    USER_HAS_SIGN_UP_4_COMPETITION(403, "选手已经报名该比赛"),

    //其他
    DATABASE_ERR(405,"数据库错误"),
    UNKNOWN_ERROR(500,"未知错误"),
    BAD_REQUEST(400, "请求参数格式有误"),
    TOKEN_ERROR(403,"TOKEN错误"),
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
