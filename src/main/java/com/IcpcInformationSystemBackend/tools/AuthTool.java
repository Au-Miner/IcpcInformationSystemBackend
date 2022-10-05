package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.UserDo;
import com.IcpcInformationSystemBackend.model.entity.UserDoExample;
import com.IcpcInformationSystemBackend.security.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        // // 获取安全上下文对象，就是那个保存在 ThreadLocal 里面的安全上下文对象
        // // 总是不为null(如果不存在，则创建一个authentication属性为null的empty安全上下文对象)
        // SecurityContext securityContext = SecurityContextHolder.getContext();
        //
        // // 获取当前认证了的 principal(当事人),或者 request token (令牌)
        // // 如果没有认证，会是 null,该例子是认证之后的情况
        // Authentication authentication = securityContext.getAuthentication();
        //
        // // 获取当事人信息对象，返回结果是 Object 类型，但实际上可以是应用程序自定义的带有更多应用相关信息的某个类型。
        // // 很多情况下，该对象是 Spring Security 核心接口 UserDetails 的一个实现类，你可以把 UserDetails 想像
        // // 成我们数据库中保存的一个用户信息到 SecurityContextHolder 中 Spring Security 需要的用户信息格式的
        // // 一个适配器。
        // Object principal = authentication.getPrincipal();
        // String username = null;
        // if (principal instanceof JwtUser) {
        //     username = ((JwtUser)principal).getId();
        // } else {
        //     username = principal.toString();
        // }
        // return username;
    }
}

