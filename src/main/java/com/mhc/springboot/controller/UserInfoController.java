package com.mhc.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.mhc.springboot.auth.pojo.UserDetailImpl;
import com.mhc.springboot.auth.service.UserDetailServiceImpl;
import com.mhc.springboot.common.utlis.JWTUtils;
import com.mhc.springboot.dao.entity.UserInfo;
import com.mhc.springboot.dao.pojo.UserDTO;
import com.mhc.springboot.dao.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mhc
 * @since 2019-10-22
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    @Qualifier(value = "userInfoServiceImpl")
    private IUserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public Object saveUserInfo(@RequestBody UserDTO userInfo) {
        try {
            LOGGER.info(JSON.toJSONString(userInfo));
            UserInfo userEntity = new UserInfo();
            userEntity.setUsername(userInfo.getUserName());
            userEntity.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userEntity.setNickname("abc");
            userEntity.setEmail("aaa");
            userInfoService.save(userEntity);
            return "success";
        }catch (Exception e){
            LOGGER.error("save error",e);
            return "failed";
        }
    }


    @PostMapping("/login")
    public Object loginUser(@RequestBody UserDTO userInfo) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUserName(), userInfo.getPassword()));
            UserDetailImpl userDetails = (UserDetailImpl) userDetailService.loadUserByUsername(userInfo.getUserName());

            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", userDetails.getUserId());
            claims.put("userName", userDetails.getUsername());
            String token = JWTUtils.encode("userInfo", claims);
            return token;
        } catch (DisabledException e){
            LOGGER.error("账户未激活",e);
            return e.toString();
        } catch (BadCredentialsException e){
            LOGGER.error("账户密码不符",e);
            return e.toString();
        } catch (AuthenticationException e) {
            LOGGER.error("登录失败",e);
            return e.toString();
        }
    }


}
