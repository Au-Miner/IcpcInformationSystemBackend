package com.IcpcInformationSystemBackend.service.Impl;

import com.IcpcInformationSystemBackend.exception.AllException;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.entity.UserDo;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.model.response.UserInfoResponse;
import com.IcpcInformationSystemBackend.service.UserService;
import com.IcpcInformationSystemBackend.tools.AuthTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private AuthTool authTool;

    @Override
    public Result getSelfUserInfo() {
        UserDo userDo;
        try {
            userDo = authTool.getUser();
        } catch (AllException e) {
            return ResultTool.error(EmAllException.NO_SUCH_USER);
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userDo, userInfoResponse);
        return ResultTool.success(userInfoResponse);
    }
}
