package com.mhc.springboot.dao.service.impl;


import com.alibaba.fastjson.JSON;
import com.mhc.springboot.base.BaseTest;
import com.mhc.springboot.common.PageUtils;
import com.mhc.springboot.dao.entity.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;


public class UserInfoServiceImplTest extends BaseTest {

    @Autowired
    private UserInfoServiceImpl userInfoService;


    @Test
    public void TestInsert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname("abcde");
        userInfo.setUsername("mhc");
        userInfo.setPassword("1234");
        userInfoService.save(userInfo);


        UserInfo entity = userInfoService.findByUserNameAndPassword("mhc", "1234");
        Assert.assertNotNull(entity);
        System.out.println(JSON.toJSONString(entity));


        entity.setUsername(UUID.randomUUID().toString());
        userInfoService.saveOrUpdate(entity);

        List<UserInfo> list = userInfoService.listByInfo(PageUtils.getDefaultPage());


        userInfoService.removeById(entity.getId());
        entity = userInfoService.findByUserNameAndPassword("mhc", "1234");
        Assert.assertNull(entity);
        System.out.println(JSON.toJSONString(entity));

    }

}