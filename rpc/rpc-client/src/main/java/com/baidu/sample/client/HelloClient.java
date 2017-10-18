package com.baidu.sample.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tingyun on 2017/10/18.
 *  rpc测试客户端
 */
public class HelloClient {

    public static void main(String[] args) {
        //1.
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
    }
}
