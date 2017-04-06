package com.hawkeys.client.message.api;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public interface Message {

    void addMessage(String message);

    void sendMessage();

    String getMessageType();

}
