package com.hawkeys.core.async;

import com.hawkeys.common.message.api.Message;
import com.hawkeys.core.server.ServiceProviderChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;

/**
 * Created by shenwenbo on 16/8/24.
 */
public class AsyncServiceRunnable<T> implements Callable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncServiceRunnable.class);

    //private ChannelHandlerContext ctx;
    private ServiceProviderChannel channel;
    private Message message;

    public AsyncServiceRunnable(ServiceProviderChannel channel, Message message) {
        this.channel = channel;
        this.message = message;
    }

    @Override
    public Object call() throws Exception {
        //信息保存数据库;
        return null;
    }
}
