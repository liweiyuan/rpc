package com.baidu.sample.client;

import com.baidu.rpc.proxy.RpcProxy;
import com.baidu.sample.api.HelloService;
import com.baidu.sample.api.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tingyun on 2017/10/19.
 * 传参为一个对象
 * @author tingyun
 */
public class HelloClient2 {

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
        RpcProxy rpcProxy= (RpcProxy) ctx.getBean("rpcProxy");
        //获取服务
        HelloService helloService=rpcProxy.create(HelloService.class);
        String result=helloService.hello(new Person("wade","US"));
        System.out.println(result);
        System.exit(1);
    }
}
