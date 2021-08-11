package com.azure.spring.core.properties.http;

public class HttpProperties {

    public static final String PREFIX = "spring.cloud.azure.http";

    private HttpClientOptionsProperties client;

    public HttpClientOptionsProperties getClient() {
        return client;
    }

    public void setClient(HttpClientOptionsProperties client) {
        this.client = client;
    }
}
