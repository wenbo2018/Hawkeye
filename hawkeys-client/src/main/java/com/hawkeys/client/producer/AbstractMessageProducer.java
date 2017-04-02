package com.hawkeys.client.producer;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public abstract class AbstractMessageProducer implements Producer {


    private  void logError(Throwable throwable) {
        logError(null,throwable);
    }

    protected abstract void logError(String error,Throwable throwable);


    protected abstract void logEvent(String type, String name, String status, String nameValuePairs);

}
