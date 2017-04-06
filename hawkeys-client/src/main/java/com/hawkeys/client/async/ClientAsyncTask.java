package com.hawkeys.client.async;

import com.hawkeys.client.remoting.Client;
import com.hawkeys.client.message.api.Message;

import java.util.concurrent.Callable;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public class ClientAsyncTask implements Callable {

    private Client client;
    private Message message;

    private ClientAsyncTask(Client client,Message message) {
        this.client=client;
        this.message=message;
    }

    @Override
    public Object call() throws Exception {
        client.sendMessage(message);
        return null;
    }

}
