package com.hawkeys.client;

import com.hawkeys.client.producer.MessageProducer;
import com.hawkeys.common.extension.UserServiceLoader;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public class HawkeysMonitor {

    private static HawkeysMonitor instance = new HawkeysMonitor();
    private static volatile boolean isInit = false;
    private MessageManager messageManager;
    private MessageProducer messageProducer;

    private static HawkeysMonitor getInstance() {
        if (!isInit) {
            if (!isInit) {
                instance.init();
            }
        }
        return instance;
    }

    private void init() {
        messageProducer = UserServiceLoader.newExtension(MessageProducer.class);
        messageManager = UserServiceLoader.newExtension(MessageManager.class);
    }

    public static MessageProducer getProducer() {
        MessageProducer messageProducer = instance.messageProducer;
        if (messageProducer != null) {
            return messageProducer;
        }
        return null;
    }

    public static void logError(String message, Throwable cause) {
        try {
            HawkeysMonitor.getProducer().logError(message,cause);
        } catch (Exception e) {

        }
    }

}
