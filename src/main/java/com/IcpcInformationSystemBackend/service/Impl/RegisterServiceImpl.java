package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.EmailCodeDoMapper;
import com.IcpcInformationSystemBackend.dao.SchoolDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.RegisterSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ReigsterUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.SchoolIdResponse;
import com.IcpcInformationSystemBackend.service.RegisterService;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.IcpcInformationSystemBackend.model.ImportantRepository.emailAuthenticationCode;

@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private EmailCodeDoMapper emailCodeDoMapper;

    @Resource
    private SchoolDoMapper schoolDoMapper;

    @Resource
    private UserDoMapper userDoMapper;

    @Override
    public Result getEmailCode(String emailAddress) {
        try {
            HtmlEmail email = new HtmlEmail();  //创建一个HtmlEmail实例对象
            email.setHostName("smtp.qq.com");   //邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
            email.setCharset("utf-8");          //设置发送的字符类型
            email.addTo(emailAddress);          //设置收件人
            email.setFrom("358671982@qq.com","ICPC上海大学");                      //发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication("358671982@qq.com", emailAuthenticationCode);   //设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject("测试");            //设置发送主题
            String code = generateCode();
            email.setMsg("您的验证码为" + code + "，有效期10分钟，请及时使用，不要告诉他人");          //设置发送内容
            email.send();                       //进行发送

            java.util.Date date = new Date();//获得当前时间
            Timestamp tim = new Timestamp(date.getTime());//将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
            emailCodeDoMapper.deleteByPrimaryKey(emailAddress);
            EmailCodeDo emailCodeDo = new EmailCodeDo();
            emailCodeDo.setEmail(emailAddress);
            emailCodeDo.setTimeOfCode(tim);
            emailCodeDo.setVerificationCode(code);
            emailCodeDoMapper.insert(emailCodeDo);

            return ResultTool.success();
        } catch (EmailException e) {
            e.printStackTrace();
            return ResultTool.error(EmAllException.EMAIL_SEND_ERROR);
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
    public Result registerSchool(RegisterSchoolInfo registerSchoolInfo) {
        SchoolDoExample schoolDoExample = new SchoolDoExample();
        schoolDoExample.createCriteria().andSchoolIdEqualTo(registerSchoolInfo.getSchoolId());
        if (schoolDoMapper.countByExample(schoolDoExample) > 0)
            return ResultTool.error(EmAllException.SCHOOL_HAVE_REGISTERED);
        SchoolDo schoolDo = new SchoolDo();
        BeanUtils.copyProperties(registerSchoolInfo, schoolDo);
        schoolDo.setState(1);

        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andEmailEqualTo(registerSchoolInfo.getEmail());
        if (userDoMapper.countByExample(userDoExample) > 0)
            return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(registerSchoolInfo, userDo);
        userDo.setIdentity(4);
        userDo.setState(1);
        userDo.setUserId(generateUserId());


        switch (judgeEmailCode(registerSchoolInfo.getEmail(), registerSchoolInfo.getEmailCode())) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_ERROR);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }

        if (schoolDoMapper.insertSelective(schoolDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (userDoMapper.insertSelective(userDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public String generateUserId() {
        while (true) {
            StringBuilder tmp = new StringBuilder();
            Calendar ca = Calendar.getInstance();
            tmp.append(ca.get(Calendar.YEAR));
            tmp = new StringBuilder(tmp.substring(2));
            if (ca.get(Calendar.MONTH) < 10)
                tmp.append(0);
            tmp.append(ca.get(Calendar.MONTH));
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < 6; i++)
                tmp.append(random.nextInt(10));
            UserDoExample userDoExample = new UserDoExample();
            userDoExample.createCriteria().andUserIdEqualTo(String.valueOf(tmp));
            if (userDoMapper.countByExample(userDoExample) == 0)
                return String.valueOf(tmp);
        }
    }

    @Override
    public Result reigsterUser(ReigsterUserInfo reigsterUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andEmailEqualTo(reigsterUserInfo.getEmail());
        if (userDoMapper.countByExample(userDoExample) > 0)
            return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);

        userDoExample.clear();
        userDoExample.createCriteria().andSchoolIdEqualTo(reigsterUserInfo.getSchoolId());
        if (userDoMapper.countByExample(userDoExample) == 0)
            return ResultTool.error(EmAllException.SCHOOL_HAVENOT_REGISTERED);

        switch (judgeEmailCode(reigsterUserInfo.getEmail(), reigsterUserInfo.getEmailCode())) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_ERROR);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(reigsterUserInfo, userDo);
        userDo.setUserId(generateUserId());
        userDo.setState(1);
        if (userDoMapper.insertSelective(userDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }

    @Override
    public Result getSchoolIdAndName() {
        try {
            SchoolDoExample schoolDoExample = new SchoolDoExample();
            schoolDoExample.createCriteria().getAllCriteria();
            List<SchoolDo> schoolDos = schoolDoMapper.selectByExample(schoolDoExample);
            List<SchoolIdResponse> resList = new ArrayList<>();
            for (SchoolDo schoolDo : schoolDos) {
                SchoolIdResponse schoolIdResponse = new SchoolIdResponse();
                BeanUtils.copyProperties(schoolDo, schoolIdResponse);
                resList.add(schoolIdResponse);
            }
            return ResultTool.success(resList);
        }catch (Exception e) {
            return ResultTool.error(EmAllException.UNKNOWN_ERROR);
        }
    }

    @Override
    public int judgeEmailCode(String email, String emailCode) {
        EmailCodeDoExample emailCodeDoExample = new EmailCodeDoExample();
        emailCodeDoExample.createCriteria().andEmailEqualTo(email);
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
