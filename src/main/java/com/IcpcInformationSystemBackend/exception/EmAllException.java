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
    NO_SUCH_FILE(403, "找不到该文件"),
    FILE_FORMAT_ERROR(403, "文件格式错误"),
    FILE_TOO_LARGE(403, "文件大小过大"),

    //学校相关
    NO_SUCH_SCHOOL(403, "查无此校"),
    SCHOOL_HAVE_REGISTERED(403, "学校已注册"),
    SCHOOL_NOT_REGISTERED(405,"学校未注册"),
    SCHOOL_DONT_NEED_APPROVE(405,"学校无需审批"),
    SCHOOL_DONT_APPROVE_SUCCESS(403, "学校未审批通过"),

    //人相关
    NO_SUCH_USER(403, "查无此人"),
    PASSWD_WRONG(403, "密码错误"),
    USER_IDENTITY_ERROR(403, "用户身份错误"),
    USER_DONT_NEED_APPROVE(403, "用户无需审批"),
    USER_DONT_APPROVE_SUCCESS(403, "用户未审批通过"),
    AUTHORIZATION_ERROR(403, "权限错误"),
    ID_CARD_FORMAT_ERROR(403, "身份证格式错误"),
    ID_CARD_HAS_REGISTERED(403, "身份证已注册"),

    //比赛相关
    NO_SUCH_COMPETITION(403,"查无此赛"),
    COMPETITION_DONT_NEED_APPROVE(403,"比赛无需审批"),
    COMPETITION_STATE_ERROR(403, "比赛状态有误"),
    COMPETITION_NOT_START(403, "比赛报名还未开始"),
    COMPETITION_HAS_END(403, "比赛报名已经结束"),
    COMPETITION_CERTIFICATE_FAKE(403, "比赛证书有误"),
    USER_HAS_PARTICIPATED_IN_2_REGIONAL_COMPETITIONS_THIS_YEAR(403, "选手已经参加两届icpc区域赛了"),

    //队伍相关
    NO_SUCH_TEAM(403, "查无此队"),
    TEAM_USERS_NOT_SAME_SCHOOL(403, "队伍成员不属于同一学校"),
    TEAM_DONT_NEED_APPROVE(403, "队伍无需审批"),
    USER_HAS_SIGN_UP_4_COMPETITION(403, "选手已经报名该比赛"),
    USER_NOT_SIGN_UP_4_COMPETITION(403, "选手没有报名该比赛"),
    TEAM_DONT_APPROVE_SUCCESS(403, "队伍未审批通过"),
    TEAM_SCORE_DONT_RELEASE(403, "比赛结果还未发布"),

    //场地相关
    NO_SUCH_POSITION(403, "查无此场"),
    POSITION_CAPACITY_NOT_ENOUGH(403, "场地容量不足"),
    TEAM_DONT_ASSIGN_POSITION(403, "队伍比赛场地暂未分配"),

    //其他
    DATABASE_ERR(405,"数据库错误"),
    UNKNOWN_ERROR(500,"未知错误"),
    BAD_REQUEST(400, "请求参数格式有误"),
    TOKEN_ERROR(403,"TOKEN错误"),
    VERIFICATION_CODE_ERROR(403,"验证码错误"),
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
