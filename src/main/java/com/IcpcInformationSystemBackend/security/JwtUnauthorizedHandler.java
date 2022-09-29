package com.IcpcInformationSystemBackend.security;

import com.IcpcInformationSystemBackend.tools.ResultTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/*
 * @Author:yuban00018
 * @Date:2022/1/31
 * @Description: 处理未登录用户
 */
@Slf4j
@Component
public class JwtUnauthorizedHandler implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), ResultTool.error(HttpServletResponse.SC_UNAUTHORIZED, "请先登录"));
    }
}
