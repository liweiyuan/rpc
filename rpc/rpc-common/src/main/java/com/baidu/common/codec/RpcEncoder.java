package com.baidu.common.codec;

import com.baidu.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by spark on 17-10-17.
 */
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass){
        this.genericClass=genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          Object in, ByteBuf out) throws Exception {
        if(genericClass.isInstance(in)){
            byte[] data= SerializationUtil.serialize(in);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
