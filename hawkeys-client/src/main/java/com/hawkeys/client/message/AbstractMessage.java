package com.hawkeys.client.message;


import com.hawkeys.client.MessageManager;
import com.hawkeys.client.message.api.Message;
import com.hawkeys.common.extension.UserServiceLoader;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public abstract class AbstractMessage implements Message {

    /***
     * 消息类型;
     */
    private String messageType;

    private String messageName;

    private long m_timestampInMillis;

    private String data;


    public AbstractMessage(String type, String name) {
        messageType = String.valueOf(type);
        messageName = String.valueOf(name);
        m_timestampInMillis = System.currentTimeMillis();
    }

    @Override
    public void addMessage(String message) {
        if (data==null) {
            data=message;
        }
    }

    @Override
    public String getMessageType() {
        return this.messageType ;
    }
}
