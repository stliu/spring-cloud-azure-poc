package com.azure.spring.core.properties.http;

import com.azure.spring.core.aware.client.HttpClientAware;

public class HttpProperties implements HttpClientAware {

    public static final String PREFIX = "spring.cloud.azure.http";

    private HttpClientProperties client;

    @Override
    public HttpClientProperties getClient() {
        return client;
    }

    @Override
    public void setClient(HttpClientProperties client) {
        this.client = client;
    }
}
