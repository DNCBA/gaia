package com.mhc.springboot.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mhc.springboot.dao.entity.UserInfo;
import com.mhc.springboot.dao.mapper.UserInfoMapper;
import com.mhc.springboot.dao.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mhc
 * @since 2019-10-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    public UserInfo findByUserNameAndPassword(String username, String password) {
        return getOne(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getUsername, username)
                .eq(UserInfo::getPassword, password)
        );
    }

    public List<UserInfo> listByInfo(Page<Object> page) {
        return getBaseMapper().listByInfo(page);
    }

    public UserInfo findByUserName(String userName) {
        return getOne(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getUsername, userName)
        );
    }

    public UserInfo findById(Long userId) {
        return getOne(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getId, userId)
        );
    }
}
