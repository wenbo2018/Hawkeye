package com.hawkeys.core.server;

import com.hawkeys.client.message.api.Message;
import com.hawkeys.core.process.RequestProcessor;
import com.hawkeys.core.process.RequestProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public abstract class AbstractServer implements Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServer.class);

    private RequestProcessor requestProcessor;

    private ServerConfig serverConfig = null;

    private int port;

    protected abstract void doStart(ServerConfig serverConfig);

    protected abstract void doStop();

    @Override
    public RequestProcessor star(ServerConfig serverConfig) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("server config:" + serverConfig);
        }
        doStart(serverConfig);
        this.port=serverConfig.getPort();
        this.serverConfig=serverConfig;
        this.requestProcessor= RequestProcessorFactory.selectProcessor();
        return this.requestProcessor;
    }

    public void stop() {
        doStop();
    }


    public void processRequest(Message message, ServiceProviderChannel serviceProviderChannel){
        this.requestProcessor.processRequest(message,serviceProviderChannel);
    }

    @Override
    public RequestProcessor getRequestProcessor() {
        return this.requestProcessor;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
