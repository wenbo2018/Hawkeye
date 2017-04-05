package com.hawkeys.netty.invoke;



import com.hawkeys.client.api.Client;
import com.hawkeys.client.remoting.ClientFactory;
import com.hawkeys.common.ConnectInfo;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by wenbo2018 on 2016/8/26.
 */
public class NettyClientFactory implements ClientFactory {

    private static Logger LOGGER= LoggerFactory.getLogger(NettyClientFactory.class);

    EventLoopGroup group;

    Map<String,Channel> channelMap=new ConcurrentHashMap<String, Channel>();

    ConcurrentHashMap<String,Client> clients=new ConcurrentHashMap<String,Client>();

    private static volatile boolean isStartup = false;

    @Override
    public void init() {
        if (!isStartup) {
            this.group = new NioEventLoopGroup(4);
            LOGGER.info("NettyClientFactory is initial");
        }
    }

    @Override
    public Client createClient(ConnectInfo connectInfo) {
        //TODO: 2017/3/23 暂时未测试
        Client client=new NettyClient(this.group,connectInfo);
        client.connect();
        clients.put(connectInfo.getHost()+"-"+connectInfo.getPort(),client);
        return client;
    }

    @Override
    public Client getClient(ConnectInfo connectInfo) {
        Client client=clients.get(connectInfo.getHost()+"-"+connectInfo.getPort());
        if (client==null) {
            client=new NettyClient(this.group,connectInfo);
            client.connect();
            clients.put(connectInfo.getHost()+"-"+connectInfo.getPort(),client);
            return client;
        }
        return client;
    }
}
