package com.hawkeys.netty.invoke;

import com.hawkeys.client.remoting.AbstractClient;
import com.hawkeys.common.config.ConnectInfo;
import com.hawkeys.client.message.api.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by wenbo2018 on 2016/8/26.
 */
public class NettyClient extends AbstractClient {

    private static Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);

    private Channel channel;

    private Bootstrap bootstrap = new Bootstrap();

    private volatile boolean connected = false;

    private ConnectInfo connectInfo;

    public NettyClient(EventLoopGroup group, ConnectInfo connectInfo) {
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ClientChannelInitializer(this));
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        this.connectInfo = connectInfo;
    }

    @Override
    protected void doConnect() {
        if (this.connected) {
            return;
        }
        // 连接消息处理服务器
        String host = connectInfo.getHost();
        int port = connectInfo.getPort();
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            LOGGER.error("connect rpc server:{}", e);
        }
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                LOGGER.info("FOX SERVICE SUCCESS CONNECT");
            }
        });
        if (channelFuture.isSuccess()) {
            this.channel = channelFuture.channel();
            this.connected = true;
        }
    }

    @Override
    protected void doWrite(Message message) {
        ChannelFuture future = null;
        future = channel.writeAndFlush(message);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    LOGGER.info("hawkeys success send message");
                    return;
                }
            }
        });
    }

    public void reConnect() {
        if (channel != null && channel.isActive()) {
            return;
        }
        // 连接 RPC 服务器
        String host = connectInfo.getHost();
        int port = connectInfo.getPort();
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(host, port).sync();
        } catch (InterruptedException e) {
            LOGGER.error("connect rpc server:{}", e);
        }
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                LOGGER.info("HAWKEYS CLIENT SUCCESS RESTART");
            }
        });
        if (channelFuture.isSuccess()) {
            this.channel = channelFuture.channel();
            this.connected = true;
        }
    }

}
