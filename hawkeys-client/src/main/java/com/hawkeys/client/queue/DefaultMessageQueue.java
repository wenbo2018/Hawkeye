package com.hawkeys.client.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public class DefaultMessageQueue<T> implements MessageQueue<T> {

    private BlockingQueue<T> messageQueue;

    public DefaultMessageQueue(int size) {
        messageQueue = new ArrayBlockingQueue<T>(size);
    }

    @Override
    public boolean offer(T t) {
        return messageQueue.offer(t);
    }

    @Override
    public T poll() {
        try {
            return messageQueue.poll(5, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return null;
        }

    }

    @Override
    public int size() {
        return messageQueue.size();
    }

    @Override
    public T peek() {
        return messageQueue.peek();
    }
}
