package com.baidu.rpc.proxy.client;

import com.baidu.common.bean.RpcRequest;
import com.baidu.common.bean.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tingyun on 2017/10/18.
 */
public class RpcClient extends SimpleChannelInboundHandler<RpcResponse>{
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClient.class);

    private final String host;
    private final int port;
    private RpcResponse response;
    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse response) throws Exception {
        this.response=response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("api caught exception", cause);
        ctx.close();
    }

    //发送请求，获取对应的响应
    public RpcResponse send(RpcRequest request) {
        EventLoopGroup group=new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
        try{

        }finally {
           group.shutdownGracefully();
        }
        return null;
    }
}
