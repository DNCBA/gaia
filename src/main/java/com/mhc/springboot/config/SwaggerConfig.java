package com.mhc.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-17 15:28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${applicatioName:gaia}")
    private String applicationName;
    @Value("${applicationURL:localhost:8080}")
    private String applicationURL;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mhc.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(String.format("%s 项目接口文档",applicationName))
                .description("基于 swagger2 实现的接口文档")
                .termsOfServiceUrl(applicationURL)
                .version("1.0")
                .build();
    }
}
