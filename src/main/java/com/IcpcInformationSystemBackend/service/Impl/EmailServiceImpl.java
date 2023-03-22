package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.service.EmailService;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import static com.IcpcInformationSystemBackend.model.ImportantRepository.emailAuthenticationCode;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    // @Resource
    // private EmailCodeDoMapper emailCodeDoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Result sendEmailCode(String emailAddress) {
        try {
            HtmlEmail email = new HtmlEmail();  //创建一个HtmlEmail实例对象
            email.setHostName("smtp.qq.com");   //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
            email.setCharset("utf-8");          //设置发送的字符类型
            email.addTo(emailAddress);          //设置收件人
            email.setFrom("1840347063@qq.com","ICPC上海大学");                      //发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication("1840347063@qq.com", emailAuthenticationCode);   //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject("验证码");            //设置发送主题
            String code = generateCode();
            email.setMsg("您的验证码为" + code + "，有效期10分钟，请及时使用，不要告诉他人");          //设置发送内容
            email.send();                       //进行发送

            redisTemplate.opsForValue().set(emailAddress, code);
            redisTemplate.expire(emailAddress, Duration.ofSeconds(600));
            // java.util.Date date = new Date();//获得当前时间
            // Timestamp tim = new Timestamp(date.getTime());//将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
            // emailCodeDoMapper.deleteByPrimaryKey(emailAddress);
            // EmailCodeDo emailCodeDo = new EmailCodeDo();
            // emailCodeDo.setUserEmail(emailAddress);
            // emailCodeDo.setTimeOfCode(tim);
            // emailCodeDo.setVerificationCode(code);
            // emailCodeDoMapper.insert(emailCodeDo);
            return ResultTool.success();
        } catch (EmailException e) {
            e.printStackTrace();
            return ResultTool.error(EmAllException.EMAIL_SEND_WRONG);
        }
    }

    @Override
    public String generateCode() {
        StringBuilder code = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(36);
            if (number < 10)
                code.append((char) ('0' + number));
            else
                code.append((char) ('A' + number - 10));
        }
        return code.toString();
    }

    @Override
    public Result sendEmailMessage(String userEmail, String message) {
        try {
            HtmlEmail email = new HtmlEmail();  //创建一个HtmlEmail实例对象
            email.setHostName("smtp.qq.com");   //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
            email.setCharset("utf-8");          //设置发送的字符类型
            email.addTo(userEmail);          //设置收件人
            email.setFrom("1840347063@qq.com","ICPC上海大学");                      //发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication("1840347063@qq.com", emailAuthenticationCode);   //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject("邮件通知");            //设置发送主题
            String code = generateCode();
            email.setMsg(message);          //设置发送内容
            email.send();                       //进行发送
            return ResultTool.success();
        } catch (EmailException e) {
            e.printStackTrace();
            return ResultTool.error(EmAllException.EMAIL_SEND_WRONG);
        }
    }
}
