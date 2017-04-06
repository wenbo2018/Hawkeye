package com.hawkeys.client.message;

import com.hawkeys.client.MessageManager;
import com.hawkeys.client.message.api.EventMessage;
import com.hawkeys.common.extension.UserServiceLoader;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public class DefaultEvent extends AbstractMessage implements EventMessage {

    public DefaultEvent(String type, String name) {
        super(type, name);
    }

    @Override
    public void sendMessage() {
        MessageManager messageManager= UserServiceLoader.getExtension(MessageManager.class);
        messageManager.sendMessage(this);
    }

}
