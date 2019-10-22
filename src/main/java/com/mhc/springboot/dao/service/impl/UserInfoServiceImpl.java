package com.mhc.springboot.dao.service.impl;

import com.mhc.springboot.dao.entity.UserInfo;
import com.mhc.springboot.dao.mapper.UserInfoMapper;
import com.mhc.springboot.dao.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mhc
 * @since 2019-10-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
