package com.IcpcInformationSystemBackend.tools;


import com.IcpcInformationSystemBackend.dao.EmailCodeDoMapper;
import com.IcpcInformationSystemBackend.model.entity.EmailCodeDo;
import com.IcpcInformationSystemBackend.model.entity.EmailCodeDoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class EmailTool {

    @Resource
    private EmailCodeDoMapper emailCodeDoMapper;
    public int judgeEmailCode(String email, String emailCode) {
        EmailCodeDoExample emailCodeDoExample = new EmailCodeDoExample();
        emailCodeDoExample.createCriteria().andUserEmailEqualTo(email);
        List<EmailCodeDo> emailCodeDos = emailCodeDoMapper.selectByExample(emailCodeDoExample);
        if (emailCodeDos.isEmpty())
            return 1; // 邮箱验证码错误
        Calendar ca = Calendar.getInstance();
        int day1 = ca.get(Calendar.DAY_OF_MONTH);
        int hour1 = ca.get(Calendar.HOUR_OF_DAY);
        int min1 = ca.get(Calendar.MINUTE);
        int day2 = emailCodeDos.get(0).getTimeOfCode().getDate();
        int hour2 = emailCodeDos.get(0).getTimeOfCode().getHours();
        int min2 = emailCodeDos.get(0).getTimeOfCode().getMinutes();
        int a1 = min1 + hour1 * 60 + day1 * 24 * 60;
        int a2 = min2 + hour2 * 60 + day2 * 24 * 60;
        if (a1 - a2 > 10)
            return 2; // 邮箱验证码超时
        if (!Objects.equals(emailCodeDos.get(0).getVerificationCode(), emailCode))
            return 1; // 邮箱验证码错误
        return 0;
    }
}
