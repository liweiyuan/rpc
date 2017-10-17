package com.baidu.sample.api;

/**
 * Created by tingyun on 2017/10/17.
 */
public interface HelloService {
    String hello(String name);

    String hello(Person person);
}
