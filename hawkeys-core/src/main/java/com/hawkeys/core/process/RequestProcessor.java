package com.hawkeys.core.process;

import com.hawkeys.common.message.api.Message;
import com.hawkeys.core.server.ServiceProviderChannel;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public interface RequestProcessor<T> {

    public void start();

    public void processRequest(Message message, ServiceProviderChannel channel);

}
