package com.IcpcInformationSystemBackend.tools;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class UriEncoder {
    public String encodeURIComponent(String input) {
        if (null == input || "".equals(input.trim()))
            return input;
        int l = input.length();
        StringBuilder o = new StringBuilder(l * 3);
        try {
            for (int i = 0; i < l; i++ ) {
                String e = input.substring(i, i + 1);
                if (isChineseChar(input.charAt(i))) {
                    byte[] b = e.getBytes("utf-8");
                    o.append(getHex(b));
                    continue;
                }
                o.append(e);
            }
            return o.toString();
        }
        catch (UnsupportedEncodingException e)
       {
            e.printStackTrace();
        }
        return input;
    }

    private static String getHex(byte buf[]) {
        StringBuilder o = new StringBuilder(buf.length * 3);
        for (int i = 0; i < buf.length; i++ ) {
            int n = (int)buf[i] & 0xff;
            o.append("%");
            if (n < 0x10) {
                o.append("0");
            }
            o.append(Long.toString(n, 16).toUpperCase());
        }
        return o.toString();
    }

    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }
}
