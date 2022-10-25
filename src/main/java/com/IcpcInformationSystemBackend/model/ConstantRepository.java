package com.IcpcInformationSystemBackend.model;

/*
 * @Description:
 */
public class ConstantRepository {
    public static final int TOKEN_TIMEOUT_DAY = 5;
    public static final int TOKEN_VERIFICATION_EXCEPTION = -3;
    public static final int TOKEN_EXPIRED_EXCEPTION = -2;
    public static final int TOKEN_FAKE_EXCEPTION = -1;
    public static final int TOKEN_VALID = 1;
    public static final String TOKEN_HEADER = "Bearer ";
    public static final String TOKEN_NAME = "Authorization";


    //用户身份程序样式
    public final static String[] IDENTITY = {
            "",
            "ROLE_STUDENT",
            "ROLE_COACH",
            "ROLE_ADMINISTRATOR",
            "ROLE_CHAIRMAN",
    };

    public static final String TEAM_SCORES_SHEET_NAME = "teamScores";
}
