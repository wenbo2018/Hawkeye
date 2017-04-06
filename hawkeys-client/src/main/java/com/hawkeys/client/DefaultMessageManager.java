package com.hawkeys.client;

import com.hawkeys.client.remoting.Client;
import com.hawkeys.client.remoting.ClientManager;
import com.hawkeys.client.message.api.Message;


/**
 * Created by shenwenbo on 2017/4/6.
 */
public class DefaultMessageManager implements MessageManager {

    private ThreadLocal<MessageContext> messageContextThreadLocal = new ThreadLocal<MessageContext>();

    private String domain;

    @Override
    public void sendMessage(Message message) {
        MessageContext messageContext = getMessageContext();
        messageContext.addMessage(message);
    }

    public void sendMessage(Message message, boolean clearContext) {
        Client client = ClientManager.getInstance().getClient(message.getMessageType());
        client.sendMessage(message);
    }


    private MessageContext getMessageContext() {
        MessageContext ctx = messageContextThreadLocal.get();
        if (ctx != null) {
            return ctx;
        } else {
            if (domain != null) {
                ctx = new MessageContext(domain, null, null);
            }
            messageContextThreadLocal.set(ctx);
            return ctx;
        }
    }


    class MessageContext {
        public MessageContext(String domain, String hostName, String ipAddress) {
            Thread thread = Thread.currentThread();
            String groupName = thread.getThreadGroup().getName();
        }

        public void addMessage(Message message) {
            sendMessage(message, false);
        }
    }

}
