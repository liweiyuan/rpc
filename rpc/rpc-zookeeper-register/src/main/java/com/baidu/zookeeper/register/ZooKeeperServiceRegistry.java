package com.baidu.zookeeper.register;

/**
 * Created by tingyun on 2017/10/17.
 */

import com.baidu.register.ServiceRegistry;
import com.baidu.zookeeper.Constant;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ServiceRegistry:注册中心的接口服务
 */
public class ZooKeeperServiceRegistry implements ServiceRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperServiceRegistry.class);

    //zookeeper接口
    private final ZkClient zkClient;

    public ZooKeeperServiceRegistry(String zkAddress) {
        //创建Zookeeper客户端
        this.zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        LOGGER.debug("connect zookeeper");
    }

    //服务注册中心
    @Override
    public void register(String serviceName, String serviceAddress) {
        // 创建 registry 节点（持久）
        String registerPath = Constant.ZK_REGISTRY_PATH;
        if (!zkClient.exists(registerPath)) {
            zkClient.createPersistent(registerPath);
            LOGGER.debug("create registry node: {}", registerPath);
        }
        // 创建 service 节点（持久）
        String servicePath = registerPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            LOGGER.debug("create service node: {}", servicePath);
        }
        // 创建 address 节点（临时）
        String addresspath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addresspath, serviceAddress);
        LOGGER.debug("create address node: {}", addressNode);
    }
}
