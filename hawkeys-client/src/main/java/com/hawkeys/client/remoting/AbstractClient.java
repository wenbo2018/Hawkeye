package com.hawkeys.client.remoting;


import com.hawkeys.client.queue.DefaultMessageQueue;
import com.hawkeys.client.queue.MessageQueue;
import com.hawkeys.client.message.api.Message;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public abstract class AbstractClient implements Client {

    protected abstract void doWrite(Message message);

    protected abstract void doConnect();

    private MessageQueue<Message> messageQueue = new DefaultMessageQueue<Message>(10000);

    /***
     * 循环处理消息；
     */
    @Override
    public void start() {
        connect();
        process();
    }

    private void process() {
        new Runnable() {
            @Override
            public void run() {
                Message message = messageQueue.poll();
                if (message != null) {
                    doWrite(message);
                }
            }
        };
    }

    @Override
    public void connect() {
        this.doConnect();
    }

    @Override
    public void sendMessage(Message message) {
        this.messageQueue.offer(message);
    }
}
