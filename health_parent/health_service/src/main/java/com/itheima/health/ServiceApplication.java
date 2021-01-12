package com.itheima.health;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 包名:com.itheima.health
 *
 * @author Zhang Baoxian
 * 日期:2021-01-05   23:45:01
 */
public class ServiceApplication {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:spring-service.xml");
        //阻塞
        System.in.read();
    }
}
