package com.hawkeys.core.server;

import com.hawkeys.core.process.RequestProcessor;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public interface Server {

    public void star() throws Exception;

    public boolean isStarted();

    public RequestProcessor star(ServerConfig serverConfig) throws Exception;

    public RequestProcessor getRequestProcessor();

    int getPort();
}
