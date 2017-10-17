package com.baidu.sample.server;

import com.baidu.basic.server.RpcService;
import com.baidu.sample.api.HelloService;
import com.baidu.sample.api.Person;

/**
 * Created by tingyun on 2017/10/17.
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello,"+name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
