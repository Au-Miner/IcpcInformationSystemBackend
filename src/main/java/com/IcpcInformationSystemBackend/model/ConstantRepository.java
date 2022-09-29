package com.IcpcInformationSystemBackend.model;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
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
}
