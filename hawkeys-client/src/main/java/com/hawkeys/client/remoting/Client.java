package com.hawkeys.client.remoting;


import com.hawkeys.client.message.api.Message;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public interface Client {

    void start();

    void connect();

    void sendMessage(Message message);
}
