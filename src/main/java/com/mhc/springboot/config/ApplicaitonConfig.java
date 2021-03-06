package com.mhc.springboot.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-21 16:21
 */
@Configuration
public class ApplicaitonConfig implements ErrorPageRegistrar, WebMvcConfigurer {

    @Value("${application.db.url:jdbc:mysql://127.0.0.1:3306/mhc_gaia?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true}")
    private String dataSourceUrl;
    @Value("${application.db.username:root}")
    private String dbUsername;
    @Value("${application.db.password:1234567}")
    private String dbPassword;

    /**
     * 数据库连接配置
     */
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(dataSourceUrl)
                .username(dbUsername)
                .password(dbPassword)
                .build();
    }


    /**
     * 处理常见错误页面的相关信息
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //404页面处理
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/errorPage/404/aaa"));
        //500页面处理
        registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errorPage/500/bbb"));
    }


    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }


    /**
     * caffeine缓存
     */
    @Bean("caffeine")
    public CacheManager getCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setAllowNullValues(true);
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(64)
                .maximumSize(1024)
                .expireAfterWrite(30L, TimeUnit.SECONDS));
        return cacheManager;
    }

}
