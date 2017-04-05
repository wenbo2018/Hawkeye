package com.hawkeys.core.server;

import com.hawkeys.common.message.api.Message;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public interface ServiceProviderChannel {
    public void write(final Message message);
}
