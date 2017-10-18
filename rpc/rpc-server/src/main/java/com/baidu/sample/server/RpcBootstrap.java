package com.baidu.sample.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by spark on 17-10-17.
 */
//启动类
public class RpcBootstrap {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcBootstrap.class);

    public static void main(String[] args) {
        LOGGER.debug("start server");
        new ClassPathXmlApplicationContext("spring.xml");
    }
}
