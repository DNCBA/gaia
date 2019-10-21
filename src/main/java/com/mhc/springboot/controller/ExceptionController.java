package com.mhc.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.mhc.springboot.dao.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-15 16:34
 */
@RestController()
@RequestMapping("/exception")
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    /**
     * 测试接口是否正常
     */
    @GetMapping("/test")
    public Object test(@RequestParam(value = "info",required = true) String info){
        return info;
    }

    /**
     * 测试抛出自定义错误类型
     */
    @PostMapping("/throwRuntimeException")
    public Object throwRuntimeException(@RequestParam(value = "message",required = true) String exceptionMessage){
        LOGGER.info("exceptionController throwRuntimeException " + exceptionMessage);
        throw new RuntimeException(exceptionMessage);
    }

    /**
     * 测试用于抛出系统错误如400之类的
     */
    @PostMapping("/throwBadRequest")
    public Object throwBadRequest(@RequestParam(name = "userInfo",required = true) UserDTO userDTO){
        LOGGER.info("exceptionController throwBadRequest info : " + JSON.toJSONString(userDTO));
        return userDTO;
    }




}
