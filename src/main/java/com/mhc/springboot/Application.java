package com.mhc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-15 16:33
 */
@SpringBootApplication
@MapperScan("com.mhc.springboot.dao.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
