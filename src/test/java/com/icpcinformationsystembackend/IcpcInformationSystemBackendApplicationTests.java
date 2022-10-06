package com.icpcinformationsystembackend;

import org.junit.jupiter.api.Test;

// @SpringBootTest
class IcpcInformationSystemBackendApplicationTests {

    //用户身份程序样式
    public final static String[] IDENTITY = {
            "",
            "ROLE_STUDENT",
            "ROLE_COACH",
            "ROLE_ADMINISTRATOR",
            "ROLE_CHAIRMAN",
    };

    @Test
    void contextLoads() {
        System.out.println(IDENTITY[2]);
    }

}
