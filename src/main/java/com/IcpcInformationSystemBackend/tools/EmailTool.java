package com.IcpcInformationSystemBackend.tools;


import com.ramostear.captcha.HappyCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class EmailTool {

    // @Resource
    // private EmailCodeDoMapper emailCodeDoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public int judgeEmailCode(String email, String emailCode) {
        emailCode = codeToUpper(emailCode);
        String code = redisTemplate.opsForValue().get(email);
        if (code == null)
            return 1;
        Long ttlTime = redisTemplate.getExpire(email);
        if (ttlTime == -2)
            return 2; // 邮箱验证码超时
        if (!Objects.equals(code, emailCode))
            return 1; // 邮箱验证码错误
        redisTemplate.expire(email, Duration.ofSeconds(0));
        return 0;

        // EmailCodeDoExample emailCodeDoExample = new EmailCodeDoExample();
        // emailCodeDoExample.createCriteria().andUserEmailEqualTo(email);
        // List<EmailCodeDo> emailCodeDos = emailCodeDoMapper.selectByExample(emailCodeDoExample);
        // if (emailCodeDos.isEmpty())
        //     return 1; // 邮箱验证码错误
        // Calendar ca = Calendar.getInstance();
        // int day1 = ca.get(Calendar.DAY_OF_MONTH);
        // int hour1 = ca.get(Calendar.HOUR_OF_DAY);
        // int min1 = ca.get(Calendar.MINUTE);
        // int day2 = emailCodeDos.get(0).getTimeOfCode().getDate();
        // int hour2 = emailCodeDos.get(0).getTimeOfCode().getHours();
        // int min2 = emailCodeDos.get(0).getTimeOfCode().getMinutes();
        // int a1 = min1 + hour1 * 60 + day1 * 24 * 60;
        // int a2 = min2 + hour2 * 60 + day2 * 24 * 60;
        // if (a1 - a2 > 10)
        //     return 2; // 邮箱验证码超时
        // if (!Objects.equals(emailCodeDos.get(0).getVerificationCode(), emailCode))
        //     return 1; // 邮箱验证码错误
        // return 0;
    }

    public String codeToUpper(String code) {
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) >= 'a' && code.charAt(i) <= 'z')
                tmp.append((char) (code.charAt(i) - 'a' + 'A'));
            else
                tmp.append(code.charAt(i));
        }
        return String.valueOf(tmp);
    }

    public boolean judgeEmailFormatIfRight(String email) {
            boolean flag = false;
            String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern p;
            Matcher m;
            p = Pattern.compile(regEx1);
            m = p.matcher(email);
            if(m.matches())
                flag = true;
            return flag;
    }

    public boolean verifyVerificationCode(String code, HttpServletRequest request){
        if (HappyCaptcha.verification(request, code, true)) {
            HappyCaptcha.remove(request);
            return true;
        }
        return false;
    }
}
