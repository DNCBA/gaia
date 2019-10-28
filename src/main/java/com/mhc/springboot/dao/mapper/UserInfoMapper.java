package com.mhc.springboot.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mhc.springboot.dao.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mhc
 * @since 2019-10-28
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select * from user_info ")
    List<UserInfo> listByInfo(Page<Object> page);
}
