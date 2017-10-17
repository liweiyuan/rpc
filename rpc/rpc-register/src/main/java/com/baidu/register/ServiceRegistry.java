package com.baidu.register;

/**
 * Created by tingyun on 2017/10/17.
 * 服务发现接口
 */
public interface ServiceRegistry {

    /**
     * 注册服务名称与服务地址
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址
     */
    void register(String serviceName, String serviceAddress);
}
