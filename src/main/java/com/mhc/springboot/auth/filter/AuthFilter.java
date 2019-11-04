package com.mhc.springboot.auth.filter;


import com.mhc.springboot.auth.service.UserDetailServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-29 22:25
 */
@Component
public class AuthFilter extends OncePerRequestFilter {

    @Value("${accessWhiteList:/user/login,/user/switchUser}")
    private List<String> accessWhiteList;
    @Value("${mockPrefix:mhc}")
    private String MOCK_NAME;
    @Value("${tokenPrefix:abc}")
    private String TOKEN_PREFIX;
    private static final String AUTH_HEAD = "Authorization";

    @Autowired
    private UserDetailServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //白名单路径直接放行
        String requestURI = httpServletRequest.getRequestURI();
        if (accessWhiteList.contains(requestURI)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //非白名单数据,进行校验
        String header = httpServletRequest.getHeader(AUTH_HEAD);
        if (StringUtils.isBlank(header)) {
            //无token信息直接拒绝
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (header.startsWith(MOCK_NAME)) {
            //使用username直接mock接口
            String username = header.replaceAll(MOCK_NAME, "");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            buildUPToken(userDetails);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        } else if (header.startsWith(TOKEN_PREFIX)) {
            String token = header.replaceAll(MOCK_NAME, "");
            //可以加入单点登录功能,使用userId作为key，token作为value
            //解析token数据,从token中获取数据
            UserDetails userDetails = userDetailsService.loadUserById(8L);
            buildUPToken(userDetails);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        } else {
            //无法解析的token直接丢弃
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


    }

    /**
     * 根据userDetail构造认证对象
     */
    private void buildUPToken(UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
