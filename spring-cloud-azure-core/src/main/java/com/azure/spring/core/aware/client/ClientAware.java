package com.azure.spring.core.aware.client;


import com.azure.spring.core.properties.ClientProperties;

public interface ClientAware {
    void setClient(ClientProperties client);
    ClientProperties getClient();
}
