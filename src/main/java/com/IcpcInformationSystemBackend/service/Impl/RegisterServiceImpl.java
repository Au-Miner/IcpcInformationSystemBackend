package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.dao.EmailCodeDoMapper;
import com.IcpcInformationSystemBackend.dao.PasswordDoMapper;
import com.IcpcInformationSystemBackend.dao.SchoolDoMapper;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.*;
import com.IcpcInformationSystemBackend.model.request.RegisterSchoolInfo;
import com.IcpcInformationSystemBackend.model.request.ReigsterUserInfo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.SchoolIdResponse;
import com.IcpcInformationSystemBackend.service.RegisterService;
import com.IcpcInformationSystemBackend.tools.EmailTool;
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

    @Resource
    private PasswordDoMapper passwordDoMapper;

    @Resource
    private EmailTool emailTool;

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
            emailCodeDo.setUserEmail(emailAddress);
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
        userDoExample.createCriteria().andUserEmailEqualTo(registerSchoolInfo.getUserEmail());
        if (userDoMapper.countByExample(userDoExample) > 0)
            return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(registerSchoolInfo, userDo);
        userDo.setIdentity(4);
        userDo.setState(1);

        PasswordDo passwordDo = new PasswordDo();
        passwordDo.setUserEmail(userDo.getUserEmail());
        passwordDo.setPasswd(registerSchoolInfo.getPasswd());

        switch (emailTool.judgeEmailCode(registerSchoolInfo.getUserEmail(), registerSchoolInfo.getEmailCode())) {
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
        if (passwordDoMapper.insertSelective(passwordDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        return ResultTool.success();
    }



    @Override
    public Result reigsterUser(ReigsterUserInfo reigsterUserInfo) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andUserEmailEqualTo(reigsterUserInfo.getUserEmail());
        if (userDoMapper.countByExample(userDoExample) > 0)
            return ResultTool.error(EmAllException.EMAIL_HAVE_REGISTERED);

        userDoExample.clear();
        userDoExample.createCriteria().andSchoolIdEqualTo(reigsterUserInfo.getSchoolId());
        if (userDoMapper.countByExample(userDoExample) == 0)
            return ResultTool.error(EmAllException.SCHOOL_HAVENOT_REGISTERED);

        switch (emailTool.judgeEmailCode(reigsterUserInfo.getUserEmail(), reigsterUserInfo.getEmailCode())) {
            case 1:
                return ResultTool.error(EmAllException.EMAIL_CODE_ERROR);
            case 2:
                return ResultTool.error(EmAllException.EMAIL_CODE_OVERTIME);
            default:
                break;
        }

        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(reigsterUserInfo, userDo);
        userDo.setState(1);

        PasswordDo passwordDo = new PasswordDo();
        passwordDo.setUserEmail(userDo.getUserEmail());
        passwordDo.setPasswd(reigsterUserInfo.getPasswd());

        if (userDoMapper.insertSelective(userDo) == 0)
            return ResultTool.error(EmAllException.DATABASE_ERR);
        if (passwordDoMapper.insertSelective(passwordDo) == 0)
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
}
