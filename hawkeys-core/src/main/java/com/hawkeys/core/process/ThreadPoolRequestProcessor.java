package com.hawkeys.core.process;

import com.hawkeys.client.message.api.Message;
import com.hawkeys.core.async.AsyncServiceRunnable;
import com.hawkeys.core.server.ServiceProviderChannel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by shenwenbo on 2017/4/5.
 */
public class ThreadPoolRequestProcessor<T> implements RequestProcessor<T>{

    private static int MAX_QUEUE_SIZE;
    private static int CORE_POOL_SIZE;
    private static int MAX_POOL_SIZE;

    private static volatile ThreadPoolExecutor executorService = null;

    public ThreadPoolRequestProcessor () {
        if(executorService == null){
            if(executorService == null){
                executorService = new ThreadPoolExecutor(5, 5, 600L, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(65536));
            }
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void processRequest(Message message, ServiceProviderChannel channel) {
        AsyncServiceRunnable asyncServiceRunnable=new
                AsyncServiceRunnable(channel,message);
        executorService.submit(asyncServiceRunnable);
    }

//    @Override
//    public void processRequest(InvokeRequest invokeRequest, ServiceProviderChannel channel) {
//
//        AsyncServiceRunnable asyncServiceRunnable=new
//                AsyncServiceRunnable(channel,invokeRequest,getServiceConfig(invokeRequest));
//        executorService.submit(asyncServiceRunnable);
//    }
//
//    private ProviderConfig getServiceConfig(InvokeRequest invokeRequest) {
//        if (invokeRequest.getMessageType()== FoxConstants.MESSAGE_TYPE_HEART)
//            return null;
//        return  cacheServices.get(invokeRequest.getServiceName());
//    }
}

