package com.mhc.springboot.common.utlis;

import com.alibaba.fastjson.JSON;
import com.mhc.springboot.auth.pojo.UserDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-04 17:08
 */
public class UserUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUtils.class);

    private UserUtils() {
    }


    /**
     * 查询当前登录用户的详细信息,当未登录是返回的是一个空对象
     */
    public static UserDetailImpl getUserInfo() {
        UserDetailImpl result = new UserDetailImpl();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && authentication.getPrincipal() instanceof UserDetailImpl) {
            result = (UserDetailImpl) authentication.getPrincipal();
        }else {
            LOGGER.error("获取用户信息失败!"+ JSON.toJSONString(authentication.getPrincipal()));
        }
        return result;
    }


    /**
     * 获取当前登录用户id,当未登录返回0
     */
    public static Long getUserId() {
        Long userId = 0L;
        UserDetailImpl userInfo = getUserInfo();
        if (null != userInfo.getUserId()) {
            userId = userInfo.getUserId();
        }
        return userId;
    }

    /**
     * 获取当前登录用户userName,当未登录时返回null
     */
    public static String getUserName() {
        String userName = null;
        UserDetailImpl userInfo = getUserInfo();
        if (null != userInfo.getUsername()) {
            userName = userInfo.getUsername();
        }
        return userName;
    }


}
