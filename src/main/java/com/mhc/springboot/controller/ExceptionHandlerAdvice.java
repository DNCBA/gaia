package com.mhc.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;


/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-15 16:35
 */
@RestControllerAdvice()
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);


    @ExceptionHandler(value = RuntimeException.class )
    public Object runtimeExceptionHandler(Throwable exception, HttpServletResponse response){
        int status = response.getStatus();
        LOGGER.error("runtimeExceptionHandler " + status ,exception);
        return exception.toString();
    }

    @ExceptionHandler(value = ServletException.class)
    public Object servletExceptionHandler(Throwable exception, HttpServletResponse response){
        ServletException servletException = (ServletException) exception;
        LOGGER.error("servletExceptionHandler status" + servletException.toString() + "code" + response.getStatus());
        return "404";
    }


}
