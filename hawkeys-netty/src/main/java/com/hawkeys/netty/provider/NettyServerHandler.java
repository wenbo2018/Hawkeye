package com.hawkeys.netty.provider;

import com.hawkeys.client.message.api.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

public class NettyServerHandler extends SimpleChannelInboundHandler<Message> {

    private static Logger LOGGER=Logger.getLogger(NettyServerHandler.class);

    private NettyServer nettyServer;

    public NettyServerHandler(NettyServer nettyServer) {
        this.nettyServer=nettyServer;
    }

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, Message message) throws Exception {
        this.nettyServer.processRequest(message,new NettyChannel(ctx.channel()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("server caught exception", cause);
        ctx.close();
    }
}
