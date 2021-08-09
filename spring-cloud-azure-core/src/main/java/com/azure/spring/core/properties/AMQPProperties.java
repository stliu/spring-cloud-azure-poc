package com.azure.spring.core.properties;

public class AMQPProperties {

    public static final String PREFIX = "spring.cloud.azure.amqp";

    private ClientOptionsProperties client;

    public ClientOptionsProperties getClient() {
        return client;
    }

    public void setClient(ClientOptionsProperties client) {
        this.client = client;
    }
}
