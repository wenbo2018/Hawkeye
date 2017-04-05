package com.hawkeys.netty.code;

import com.alibaba.fastjson.JSON;
import com.hawkeys.common.message.api.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //获取消息长度
        if (in.readableBytes() < 4)
            return;
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        out.add(JSON.parseArray(new String(data),Message.class));
    }
}
