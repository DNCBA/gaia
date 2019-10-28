package com.mhc.springboot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-28 19:24
 * 设置数据库中实体公用字段数据数据
 */
@Configuration
public class MybatisPlusMetaHander implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String CREATOR_ID = "creatorId";
    private static final String UPDATER_ID = "updaterId";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName(CREATOR_ID, 1L, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName(UPDATER_ID, 1L, metaObject);
    }


    //分页插件
    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        return new PaginationInterceptor();
    }


}
