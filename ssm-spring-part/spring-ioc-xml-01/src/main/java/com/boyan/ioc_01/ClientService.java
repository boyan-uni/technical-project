package com.boyan.ioc_01;

public class ClientService {

    // 静态工厂类

    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {

        return clientService;
    }
}
