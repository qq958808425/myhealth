package com.itheima.health.exception;

/**
 * 包名:com.itheima.health.exception
 * 自定义异常 继承RuntimeException
 * @author Zhang Baoxian
 * 日期:2021-01-08   19:55:04
 */
public class MyException extends RuntimeException{
    public MyException(String message) {
        super(message);
    }
}
