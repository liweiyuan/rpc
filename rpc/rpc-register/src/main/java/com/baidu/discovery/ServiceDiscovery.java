package com.baidu.discovery;

/**
 * Created by tingyun on 2017/10/18.
 * 服务发现接口
 */
public interface ServiceDiscovery {

    /**
     * 发现服务名称
     * @param serviceName
     */
    String  discove(String serviceName);
}
