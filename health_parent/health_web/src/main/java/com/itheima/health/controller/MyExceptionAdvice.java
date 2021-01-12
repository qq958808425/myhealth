package com.itheima.health.controller;

import com.itheima.health.entity.Result;
import com.itheima.health.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 包名:com.itheima.health.controller
 * 异常处理
 * @author Zhang Baoxian
 * 日期:2021-01-08   20:07:31
 * @RestControllerAdvice 与前端约定好的，返回的都是json数据
 */
@RestControllerAdvice
public class MyExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(MyExceptionAdvice.class);
    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public Result handleMyException(MyException e) {
        return new Result(false, e.getMessage());
    }

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleMyException(Exception e) {
        //打印异常日志
        log.error("发生未知异常", e);
        return new Result(false, "发生未知异常，请联系管理员");
    }
}
