package com.hawkeys.client.remoting;


import com.hawkeys.common.message.api.Message;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public abstract class AbstractClient implements Client {

    protected abstract void doWrite(Message message);

    protected abstract void doConnect();


    @Override
    public void start() {

    }

    @Override
    public void connect() {
        this.doConnect();
    }

    @Override
    public void sendMessage(Message message) {
        this.doWrite(message);
    }
}
