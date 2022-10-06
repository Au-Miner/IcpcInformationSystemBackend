package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Slf4j
@Component
public class AuthTool {
    @Resource
    UserDoMapper userDoMapper;

    /**
     * @Description: 获取当前用户对象
     */
    public UserDo getUser() throws AllException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDo userDo = userDoMapper.selectByPrimaryKey(userId);
        if (userDo == null) {
            throw new AllException(EmAllException.NO_SUCH_USER, "凭证对应的用户在系统中不存在！");
        }
        return userDo;
    }

    /**
     * @Description: 获取当前用户id
     */
    public String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

