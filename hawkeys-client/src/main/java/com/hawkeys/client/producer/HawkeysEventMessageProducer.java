package com.hawkeys.client.producer;


import com.hawkeys.client.message.DefaultEvent;
import com.hawkeys.client.message.api.EventMessage;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public class HawkeysEventMessageProducer extends AbstractMessageProducer {


    @Override
    public void logError(String message, Throwable cause) {
        StringWriter writer = new StringWriter(2048);
        if (message != null) {
            writer.write(message);
            writer.write(":");
        }
        cause.printStackTrace(new PrintWriter(writer));

        String detailMessage = writer.toString();

        if (cause instanceof Error) {
            logEvent("Error", cause.getClass().getName(), "ERROR", detailMessage);
        } else if (cause instanceof RuntimeException) {
            logEvent("RuntimeException", cause.getClass().getName(), "ERROR", detailMessage);
        } else {
            logEvent("Exception", cause.getClass().getName(), "ERROR", detailMessage);
        }

    }

    public void logEvent(String type, String name, String status, String nameValuePairs) {

        EventMessage event = new DefaultEvent(type, name);

        if (nameValuePairs != null && nameValuePairs.length() > 0) {
            event.addMessage(nameValuePairs);
        }
        event.sendMessage();
    }

    @Override
    public void logError(Throwable cause) {
        logError(null, cause);
    }
}
