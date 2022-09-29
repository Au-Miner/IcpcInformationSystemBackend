package com.IcpcInformationSystemBackend.security;

import com.IcpcInformationSystemBackend.dao.*;
import com.IcpcInformationSystemBackend.model.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author:yuban00018
 * @Date:2022/1/30
 * @Description:
 */
@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {
    @Resource
    UserDoMapper userDoMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //从数据库中获取这个用户
        UserDo userDo = userDoMapper.selectByPrimaryKey(userId);
        if(userDo == null) {
            throw new UsernameNotFoundException("账号或密码错误!");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userDo.getIdentity().toString()));

        return new JwtUser(userDo.getUserId(), null, userDo.getIdentity(), grantedAuthorities);
    }
}
