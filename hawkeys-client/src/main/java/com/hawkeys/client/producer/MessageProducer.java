package com.hawkeys.client.producer;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public interface MessageProducer {

    public void logError(String message, Throwable cause);

    public void logError(Throwable cause);
}
