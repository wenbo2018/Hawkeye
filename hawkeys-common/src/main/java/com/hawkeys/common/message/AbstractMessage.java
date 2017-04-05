package com.hawkeys.common.message;


import com.hawkeys.common.message.api.Message;

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


    public AbstractMessage(String type, String name) {
        messageType = String.valueOf(type);
        messageName = String.valueOf(name);
        m_timestampInMillis = System.currentTimeMillis();
    }

    @Override
    public void addMessage(String message) {

    }

    @Override
    public void sendMessage() {

    }
}
