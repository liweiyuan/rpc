package com.baidu.sample.client;

import com.baidu.rpc.proxy.RpcProxy;
import com.baidu.sample.api.HelloService;
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
        RpcProxy rpcProxy = ctx.getBean(RpcProxy.class);

        //获取服务类
        HelloService helloService=rpcProxy.create(HelloService.class);
        String result=helloService.hello("world");
        System.out.println(result);

        //获取服务类
        HelloService helloService1=rpcProxy.create(HelloService.class,"sample.version2");
        String result2=helloService1.hello("世界");
        System.err.println(result2);
        System.exit(0);
    }
}
