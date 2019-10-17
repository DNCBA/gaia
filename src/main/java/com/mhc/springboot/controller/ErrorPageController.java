package com.mhc.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-15 19:44
 */
@RestController
@RequestMapping("/errorPage")
public class ErrorPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPageController.class);

    @RequestMapping("/{code}/{info}")
    public Object errorPage(@PathVariable(name = "code") String code, @PathVariable(name = "info") String info){
        LOGGER.error("errorPageController errorPage " + code + info);
        return code+info;
    }

}
