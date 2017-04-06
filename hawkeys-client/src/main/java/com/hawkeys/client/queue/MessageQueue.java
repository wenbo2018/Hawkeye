package com.hawkeys.client.queue;

import java.util.Queue;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public interface MessageQueue<T> {

    boolean offer(T e);

    T poll();

    int size();

    T peek();
}
