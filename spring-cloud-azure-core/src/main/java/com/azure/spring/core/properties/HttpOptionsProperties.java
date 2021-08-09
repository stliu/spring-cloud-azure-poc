package com.azure.spring.core.properties;

/**
 * Http options properties for Azure SDK http pipeline.
 */
public class HttpOptionsProperties {
    public static final String PREFIX = "spring.cloud.azure.http";

    private HttpClientOptionsProperties client;

    public HttpClientOptionsProperties getClient() {
        return client;
    }

    public void setClient(HttpClientOptionsProperties client) {
        this.client = client;
    }
}
