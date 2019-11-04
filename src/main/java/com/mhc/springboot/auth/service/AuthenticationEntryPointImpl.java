package com.mhc.springboot.auth.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-30 11:57
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //可以根据AuthenticationException不同的异常类型返回不同的状态数据
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
