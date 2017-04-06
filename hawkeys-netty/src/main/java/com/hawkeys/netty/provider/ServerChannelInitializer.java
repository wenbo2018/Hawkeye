package com.hawkeys.netty.provider;

import com.hawkeys.netty.code.MessageDecoder;
import com.hawkeys.netty.code.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by shenwenbo on 2016/9/28.
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private NettyServer nettyServer;

    public ServerChannelInitializer(NettyServer nettyServer) {
        this.nettyServer=nettyServer;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new MessageDecoder()); // 解码 RPC 请求
        pipeline.addLast(new MessageEncoder()); // 编码 RPC 响应
        pipeline.addLast(new NettyServerHandler(this.nettyServer));
    }
}
