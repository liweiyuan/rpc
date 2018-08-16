package com.baidu.rpc.proxy;

import com.baidu.common.bean.RpcRequest;
import com.baidu.common.bean.RpcResponse;
import com.baidu.common.util.StringUtil;
import com.baidu.discovery.ServiceDiscovery;
import com.baidu.rpc.proxy.client.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * Created by tingyun on 2017/10/18.
 * RPC 代理（用于创建 RPC 服务代理）
 */
public class RpcProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcProxy.class);

    private String serviceAddress;
    private ServiceDiscovery serviceDiscovery;

    public RpcProxy(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public <T> T create( final Class<?> interfaceClass) {
        return create(interfaceClass, "");
    }

    @SuppressWarnings("unchecked")
    public  <T> T create( final Class<?> interfaceClass, final String serviceVersion) {
        //创建动态代理对象
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //创建RPC请求对象并设置请求属性
                        RpcRequest request = new RpcRequest();
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setInterfaceName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setServiceVersion(serviceVersion);
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);
                        //获取RPC服务
                        if (serviceDiscovery != null) {
                            String serviceName = interfaceClass.getName();
                            if (StringUtil.isNotEmpty(serviceVersion)) {
                                serviceName += "-" + serviceVersion;
                            }
                            serviceAddress = serviceDiscovery.discover(serviceName);
                            LOGGER.debug("discover service: {} => {}", serviceName, serviceAddress);
                        }
                        if (StringUtil.isEmpty(serviceAddress)) {
                            throw new RuntimeException("server address is empty");
                        }
                        // 从 RPC 服务地址中解析主机名与端口号
                        String[] addressArray = serviceAddress.split(":");
                        String ip = addressArray[0];
                        int port = Integer.parseInt(addressArray[1]);
                        // 创建 RPC 客户端对象并发送 RPC 请求
                        RpcClient rpcClient = new RpcClient(ip, port);
                        long time = System.currentTimeMillis();
                        RpcResponse response = rpcClient.send(request);
                        LOGGER.debug("time: {}ms", System.currentTimeMillis() - time);
                        if (response == null) {
                            throw new RuntimeException("response is null.");
                        }
                        //返回rpc结果
                        if (response.hasException()) {
                            throw response.getException();
                        } else {
                            return response.getResult();
                        }
                    }
                });
    }
}
