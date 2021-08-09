package com.azure.spring.core.properties;

public class HttpClientOptionsProperties extends ClientOptionsProperties implements EndpointAware {

    private String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
