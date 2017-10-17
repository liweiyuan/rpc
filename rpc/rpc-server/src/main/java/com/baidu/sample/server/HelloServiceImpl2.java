package com.baidu.sample.server;

import com.baidu.basic.server.RpcService;
import com.baidu.sample.api.HelloService;
import com.baidu.sample.api.Person;

/**
 * Created by tingyun on 2017/10/17.
 */
@RpcService(value = HelloService.class,version = "sample.version2")
public class HelloServiceImpl2 implements HelloService {
    @Override
    public String hello(String name) {
        return "你好,"+name;
    }

    @Override
    public String hello(Person person) {
        return "你好! " + person.getFirstName() + " " + person.getLastName();
    }
}
