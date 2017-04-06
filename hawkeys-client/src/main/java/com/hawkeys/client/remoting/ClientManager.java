package com.hawkeys.client.remoting;

import com.hawkeys.common.extension.UserServiceLoader;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shenwenbo on 2017/4/6.
 */
public class ClientManager {

    private static ClientManager instance = new ClientManager();
    private static volatile boolean isInit = false;
    private ClientFactory clientFactory;

    private ConcurrentHashMap<String,List<Client>> clientMaps=new ConcurrentHashMap<>();

    public static ClientManager getInstance() {
        if (!isInit) {
            if (!isInit) {
                instance.init();
                isInit = true;
            }
        }
        return instance;
    }

    private void init() {
        clientFactory = UserServiceLoader.newExtension(ClientFactory.class);
        clientFactory.init();
    }

    public Client getClient(String type) {
        List<Client> clients=clientMaps.get(type);
       return clients.get(0);
    }
}
