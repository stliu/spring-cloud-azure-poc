package com.azure.spring.core.properties.amqp;

import com.azure.spring.core.properties.ClientOptionsProperties;

public class AmqpProperties {

    public static final String PREFIX = "spring.cloud.azure.amqp";

    private AmqpRetryOptionsProperties retry;
    private AmqpProxyOptionsProperties proxy;

    private ClientOptionsProperties client;

    public ClientOptionsProperties getClient() {
        return client;
    }

    public void setClient(ClientOptionsProperties client) {
        this.client = client;
    }

    public AmqpRetryOptionsProperties getRetry() {
        return retry;
    }

    public void setRetry(AmqpRetryOptionsProperties retry) {
        this.retry = retry;
    }

    public AmqpProxyOptionsProperties getProxy() {
        return proxy;
    }

    public void setProxy(AmqpProxyOptionsProperties proxy) {
        this.proxy = proxy;
    }
}
