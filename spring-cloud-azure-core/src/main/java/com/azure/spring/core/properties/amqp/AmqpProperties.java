package com.azure.spring.core.properties.amqp;

import com.azure.spring.core.aware.client.ClientAware;
import com.azure.spring.core.properties.ClientProperties;
import com.azure.spring.core.properties.ProxyProperties;

public class AmqpProperties implements ClientAware {

    public static final String PREFIX = "spring.cloud.azure.amqp";

//    private AmqpRetryOptionsProperties retry;

    // TODO
    private ProxyProperties proxy;

    private ClientProperties client;

    public ProxyProperties getProxy() {
        return proxy;
    }

    public void setProxy(ProxyProperties proxy) {
        this.proxy = proxy;
    }

    public ClientProperties getClient() {
        return client;
    }

    public void setClient(ClientProperties client) {
        this.client = client;
    }
}
