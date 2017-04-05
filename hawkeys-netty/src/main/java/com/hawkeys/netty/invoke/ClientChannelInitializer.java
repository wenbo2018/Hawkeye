package com.hawkeys.netty.invoke;

import com.hawkeys.netty.code.MessageDecoder;
import com.hawkeys.netty.code.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by wenbo2018 on 2016/8/26.
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private NettyClient nettyClient;

    public ClientChannelInitializer(NettyClient nettyClient) {
        this.nettyClient=nettyClient;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MessageEncoder()); // 编码 RPC 请求
        pipeline.addLast(new MessageDecoder()); // 解码 RPC 响应
        pipeline.addLast(new NettyClientHandler(this.nettyClient)); // 处
    }

}
