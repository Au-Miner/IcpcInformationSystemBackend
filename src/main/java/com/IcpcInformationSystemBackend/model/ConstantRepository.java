package com.IcpcInformationSystemBackend.model;

/*
 * @Description:
 */
public class ConstantRepository {
    public final static int TOKEN_TIMEOUT_DAY = 5;
    public final static int TOKEN_VERIFICATION_EXCEPTION = -3;
    public final static int TOKEN_EXPIRED_EXCEPTION = -2;
    public final static int TOKEN_FAKE_EXCEPTION = -1;
    public final static int TOKEN_VALID = 1;
    public final static String TOKEN_HEADER = "Bearer ";
    public static final String TOKEN_NAME = "Authorization";


    //用户身份程序样式
    public final static String[] IDENTITY = {
            "",
            "ROLE_STUDENT",
            "ROLE_COACH",
            "ROLE_ADMINISTRATOR",
            "ROLE_CHAIRMAN",
    };
}
