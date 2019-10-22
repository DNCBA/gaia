package com.mhc.springboot.controller;


import com.alibaba.fastjson.JSON;
import com.mhc.springboot.dao.UserDTO;
import com.mhc.springboot.dao.entity.UserInfo;
import com.mhc.springboot.dao.service.IUserInfoService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
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

    @PostMapping("/save")
    public Object saveUserInfo(@Param("userInfo") UserDTO userInfo){
        LOGGER.info(JSON.toJSONString(userInfo));
        UserInfo userEntity = new UserInfo();
        userEntity.setUsername(userInfo.getUserName());
        userEntity.setPassword(userInfo.getPassword());
        userEntity.setNickname("abc");
        userEntity.setEmail("aaa");
        userInfoService.save(userEntity);
        return "success";
    }


}
