package com.hawkeys.core.process;

import com.hawkeys.core.extension.UserServiceLoader;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public class RequestProcessorFactory {
    public static RequestProcessor selectProcessor() {
        RequestProcessor requestProcessor = UserServiceLoader.getExtension(RequestProcessor.class);
        if (requestProcessor != null) {
            return requestProcessor;
        } else {
            return new ThreadPoolRequestProcessor();
        }
    }
}
