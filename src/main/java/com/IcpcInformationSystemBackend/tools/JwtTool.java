package com.IcpcInformationSystemBackend.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.model.entity.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import static com.IcpcInformationSystemBackend.model.ConstantRepository.*;

/*
 * @Description: Java Web Token工具类，提供生成和验证Token的功能
 */
@Slf4j
@Component
public class JwtTool {
    @Value("${ENCODE_KEY}")
    private String ENCODE_KEY;

    private static JWTVerifier jwtVerifier;

    @Resource
    private UserDoMapper userDoMapper;

    private static String encode(String str) {
        return URLEncoder.encode(str);
    }

    /**
     * @Description: 生成token
     */
    public String createJwt(String id, String name) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, TOKEN_TIMEOUT_DAY);
        name = encode(name);
        Algorithm algorithm = Algorithm.HMAC256(ENCODE_KEY.getBytes());
        return TOKEN_HEADER + JWT.create()
                .withIssuedAt(currentDate)
                .withExpiresAt(calendar.getTime())
                .withSubject(id)
                .withClaim("name", name)
                .sign(algorithm);
    }

    /**
     * @Return: int -> is token valid
     * @Description: 验证token
     */
    public int validateToken(String token) {
        log.info(token);
        try {
            Algorithm algorithm = Algorithm.HMAC256(ENCODE_KEY.getBytes());
            if (jwtVerifier == null) {
                //生成HMAC256算法的Jwt验证器
                jwtVerifier = JWT.require(algorithm).build();
            }
            jwtVerifier.verify(token);
        } catch (SignatureVerificationException e) {
            log.info("Token check failed: {}", e.toString());
            return TOKEN_VERIFICATION_EXCEPTION;
        } catch (TokenExpiredException e) {
            log.info("Token expired: {}", e.toString());
            return TOKEN_EXPIRED_EXCEPTION;
        } catch (JWTDecodeException | IllegalArgumentException e) {
            log.info("Token Invalid: {}", e.toString());
            return TOKEN_FAKE_EXCEPTION;
        }
        //解码token
        DecodedJWT decodedJWT = JWT.decode(token);
        String id = decodedJWT.getSubject();
        //验证签名
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        if (userDo == null) {
            return TOKEN_FAKE_EXCEPTION;
        }
        String name = null;
        try {
            name = URLDecoder.decode(decodedJWT.getClaim("name").asString(), String.valueOf(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (name.equals(userDo.getChiName())){
            return TOKEN_VALID;
        } else {
            return TOKEN_FAKE_EXCEPTION;
        }
    }
}
