package com.hawkeys.client.remoting;

import com.hawkeys.common.ConnectInfo;

/**
 * Created by shenwenbo on 2017/4/2.
 */
public interface ClientFactory {

    void init();

    Client createClient(ConnectInfo connectInfo);

    Client getClient(ConnectInfo connectInfo);
}
