package com.icpcinformationsystembackend;

import com.IcpcInformationSystemBackend.tools.FileTool;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IcpcInformationSystemBackendApplicationTests {
    @Test
    void contextLoads() {
        String sss = "132.000";
        int pos = sss.length() - 1;
        for (int i = 0; i < sss.length(); i++) {
            if (sss.charAt(i) == '.') {
                pos = i;
                break;
            }
        }
        sss = sss.substring(0, pos);
        System.out.println(sss);
        // System.out.println(Integer.parseInt(sss));
    }
}
