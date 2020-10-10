package com.example.demo.securityConfig;


import com.alibaba.fastjson.JSON;
import com.example.demo.model.Entity.loginEntity;
import com.example.demo.model.ValueObject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级安全验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private com.example.demo.model.service.userService userService;
    @Override
    protected void configure(HttpSecurity http)throws Exception
    {
        // 开启允许iframe 嵌套
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/course/**").hasAnyAuthority("admin","teacher","student")
                .antMatchers("/teacher/**").hasAnyAuthority("admin","teacher")
                .antMatchers("/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                //成功时
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        loginEntity loginEntity = new loginEntity();
                        UserLogin userLogin=(UserLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取当前用户
                        Object o = getUserInfo(userLogin);
                        if(o instanceof Student)
                        {
                            loginEntity.setName(((Student) o).getStuName());
                        }
                        else if(o instanceof Teacher)
                        {
                            loginEntity.setName(((Teacher) o).getTeacherName());
                        }
                        else if(o instanceof admin)
                        {
                            loginEntity.setName("管理员");
                        }
                        httpServletRequest.getSession().setAttribute("user",getUserInfo(userLogin));//将当前用户写入session
                        loginEntity.setState("success");
                        loginEntity.setAuthority(userLogin.getAuthority());
                        String res = JSON.toJSONString(loginEntity).substring(0,JSON.toJSONString(loginEntity).length()-1);
                        httpServletResponse.setContentType("application/json;charset=UTf-8");
                        PrintWriter out =httpServletResponse.getWriter();
                        out.write(res+",\"errorInfo\":null}");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        loginEntity loginEntity = new loginEntity();
                        loginEntity.setErrorInfo("用户名或密码错误");
                        String res= JSON.toJSONString(loginEntity).substring(0,JSON.toJSONString(loginEntity).length()-1);
                        httpServletResponse.setContentType("application/json;charset=UTf-8");
                        httpServletResponse.setStatus(401);
                        PrintWriter out =httpServletResponse.getWriter();
                        out.write(res+",\"authority\":null"+",\"state\":null}");
                    }
                })
                //使登录页不设限访问
                .permitAll()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 从数据库读取的用户进行身份认证
                .userDetailsService(myUserDetailsService);
                //.passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    public Object getUserInfo(UserLogin user)
    {
        if(user.getAuthority().equals(authority.student.toString()))
        {
            Student student= userService.getStuInfo(user);//装配student
            student.setStuId(user.getId());//把id也装配进student
            return student;
        }
        else if(user.getAuthority().equals(authority.teacher.toString()))
        {
            Teacher teacher = userService.getTeacherInfo(user);
            teacher.setTID(user.getId());
            return teacher;
        }
        else if(user.getAuthority().equals(authority.admin.toString()))
        {
            admin admin = new admin();
            admin.setId(user.getId());
            return admin;
        }
        return null;
    }






}
