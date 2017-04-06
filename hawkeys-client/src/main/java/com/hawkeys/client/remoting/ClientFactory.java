package com.hawkeys.client.remoting;

import com.hawkeys.common.config.ConnectInfo;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public interface ClientFactory {

    void init();

    Client createClient(ConnectInfo connectInfo);

    Client getClient(ConnectInfo connectInfo);
}
