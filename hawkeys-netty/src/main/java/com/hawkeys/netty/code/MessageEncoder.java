package com.hawkeys.netty.code;

import com.alibaba.fastjson.JSON;
import com.hawkeys.client.message.api.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public class MessageEncoder extends MessageToByteEncoder {

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {

            Message message=(Message) in;
            byte[] data = JSON.toJSONString(in).getBytes();
            //写入消息长度
            out.writeInt(data.length);
            //写入消息体
            out.writeBytes(data);
    }
}
