package com.mhc.springboot.auth.service;

import com.mhc.springboot.auth.pojo.UserDetailImpl;
import com.mhc.springboot.dao.entity.UserInfo;
import com.mhc.springboot.dao.service.impl.UserInfoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-29 22:15
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserInfoServiceImpl userInfoService;

    /**
     * security自带的根据username查询
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.findByUserName(userName);
        UserDetailImpl userDetail = buildUserDetail(userInfo);
        return userDetail;
    }


    /**
     * 自定义token携带信息的方法
     */
    public UserDetails loadUserById(Long userId) {
        UserInfo userInfo = userInfoService.findById(userId);
        UserDetailImpl userDetail = buildUserDetail(userInfo);
        return userDetail;
    }

    /**
     * 数据转换
     */
    private UserDetailImpl buildUserDetail(UserInfo userInfo) {
        if (null == userInfo) {
            throw new UsernameNotFoundException("用户名未查找到");
        }
        UserDetailImpl userDetail = new UserDetailImpl();
        userDetail.setUserId(userInfo.getId());
        userDetail.setUsername(userInfo.getUsername());
        userDetail.setPassword(userInfo.getPassword());
        userDetail.setEnabled(userInfo.getDisabled());
        return userDetail;
    }
}
