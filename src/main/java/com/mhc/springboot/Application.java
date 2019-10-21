package com.mhc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-15 16:33
 */
@SpringBootApplication
@MapperScan("com.mhc.springboot.dao")
public class Application implements ErrorPageRegistrar, WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
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
}
