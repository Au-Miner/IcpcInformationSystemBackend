package com.IcpcInformationSystemBackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.IcpcInformationSystemBackend.exception.EmAllException;
import com.IcpcInformationSystemBackend.model.response.Result;
import com.IcpcInformationSystemBackend.tools.JwtTool;
import com.IcpcInformationSystemBackend.tools.ResultTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.IcpcInformationSystemBackend.model.ConstantRepository.*;

/*
 * @Description: 请求过滤器
 */
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Resource
    JwtTool jwtTool;

    private final UserDetailsService myUserDetailService;

    public JwtTokenFilter(@Qualifier("myUserDetailService") UserDetailsService userDetailsService) {
        this.myUserDetailService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        String token = request.getHeader(TOKEN_NAME);
        if (token == null) {
            filterChain.doFilter(request, response);
        } else {
            try {
                token = token.split(" ")[1];//remove the Bearer prefix
            } catch (IndexOutOfBoundsException e){
                returnErrorMessage(response, "前缀缺失");
                return;
            }
            switch (jwtTool.validateToken(token)) {
                case TOKEN_VALID:
                    DecodedJWT decodedJWT = JWT.decode(token);
                    String userEmail = decodedJWT.getSubject();
                    UserDetails userDetails = myUserDetailService.loadUserByUsername(userEmail);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    break;
                case TOKEN_VERIFICATION_EXCEPTION:
                    returnErrorMessage(response, "token签名内容失效");
                    return;
                case TOKEN_EXPIRED_EXCEPTION:
                    returnErrorMessage(response, "token已超时");
                    return;
                case TOKEN_FAKE_EXCEPTION:
                    returnErrorMessage(response, "虚假的token");
                    return;
                default:
                    break;
            }
            filterChain.doFilter(request, response);
        }
    }

    private void returnErrorMessage(HttpServletResponse response, String msg) throws IOException {
        Result result = ResultTool.error(EmAllException.TOKEN_ERROR.getErrCode(), msg);
        response.setContentType("application.yml/json;charset=utf-8");
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String jsonOfRST = mapper.writeValueAsString(result);
        out.print(jsonOfRST);
        out.flush();
    }
}
