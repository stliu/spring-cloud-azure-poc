package com.azure.spring.core.aware.client;

import com.azure.spring.core.properties.http.HttpClientProperties;

public interface HttpClientAware {
    void setClient(HttpClientProperties client);
    HttpClientProperties getClient();
}
