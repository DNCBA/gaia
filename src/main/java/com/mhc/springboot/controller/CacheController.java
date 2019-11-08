package com.mhc.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-11-08 20:17
 */

@RestController
@RequestMapping("/cache")
public class CacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);


    /**
     * addCacheInfo
     */
    @GetMapping("/saveCacheInfo")
    @Cacheable(value = "caffeine",key = "#key")
    public Object saveCacheInfo(String key){
        LOGGER.info("saveCacheInfo ");
        return key;
    }


    /**
     * flushCacheInfo
     */
    @GetMapping("/refreshCacheInfo")
    @CachePut(value = "caffeine",key = "#key")
    public Object refreshCacheInfo(String key){
        LOGGER.info("refresh");
        return key + "refresh";
    }

    /**
     * deletedCacheInfo
     */
    @GetMapping("/deletedCacheInfo")
    @CacheEvict(value = "caffeine",key = "#key")
    public Object deletedCacheInfo(String key){
        LOGGER.info("deleted");
        return "deleted";
    }






}
