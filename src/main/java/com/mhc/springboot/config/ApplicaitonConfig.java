package com.mhc.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-21 16:21
 */
@Configuration
public class ApplicaitonConfig {

    @Value("${application.db.url:jdbc:mysql://127.0.0.1:3306/mhc_gaia?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true}")
    private String DATA_SOURCE_URL;
    @Value("${application.db.username:root}")
    private String DB_USERNAME;
    @Value("${application.db.password:1234567}")
    private String DB_PASSWORD;

    /**
     * 数据库连接配置
     */
    @Bean
    public DataSource getDataSource(){
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(DATA_SOURCE_URL)
                .username(DB_USERNAME)
                .password(DB_PASSWORD)
                .build();
    }

}
