package com.IcpcInformationSystemBackend.security.config;

import com.IcpcInformationSystemBackend.security.JwtAccessDeniedHandler;
import com.IcpcInformationSystemBackend.security.JwtTokenFilter;
import com.IcpcInformationSystemBackend.security.JwtUnauthorizedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/*
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    JwtTokenFilter jwtTokenFilter;
    @Resource
    JwtUnauthorizedHandler jwtUnauthorizedHandler;
    @Resource
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/file/preview/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/file1/**").permitAll()
                .antMatchers("/email/sendEmailCode").permitAll()
                .antMatchers("/email/sendEmailMessage").authenticated()
                .antMatchers("/administrator/**").hasRole("ADMINISTRATOR")
                .antMatchers("/chairman/**").hasRole("CHAIRMAN")
                .antMatchers("/coach/**").hasAnyRole("COACH", "CHAIRMAN")
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/competitionChairman/**").hasAnyRole("ADMINISTRATOR", "CHAIRMAN", "COACH")
                // 二阶段
                .antMatchers("/signUpOver/position/**").hasAnyRole("ADMINISTRATOR", "CHAIRMAN", "COACH")
                .antMatchers("/signUpOver/file2/**").authenticated()
                // 三阶段
                .antMatchers("/competitionOver/teamResultEnroll/**").hasAnyRole("ADMINISTRATOR", "CHAIRMAN", "COACH")
                .antMatchers("/competitionOver/competitionCertificateSearch/**").permitAll()
                .antMatchers("/competitionOver/competitionCertificate/**").authenticated()
                ;


        //请求过滤器，必须要加上，否则无法鉴权
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //对于没有登录的用户，返回401
        http.exceptionHandling().authenticationEntryPoint(jwtUnauthorizedHandler);
        //对于登录了但是权限有问题的用户，返回403
        http.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler);
    }

}
