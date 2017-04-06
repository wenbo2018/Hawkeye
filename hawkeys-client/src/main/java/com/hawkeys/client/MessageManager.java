package com.hawkeys.client;

import com.hawkeys.client.message.api.Message;

/**
 * Created by shenwenbo on 2017/4/6.
 */
public interface MessageManager {
    void sendMessage(Message message);
}
