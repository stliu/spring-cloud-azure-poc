package com.azure.spring.core.properties;

import java.util.List;

public class ClientOptionsProperties implements ApplicationIdAware, HeadersAware {

    private String applicationId;

    private List<HeaderProperties> headers;

    public String getApplicationId() {
        return applicationId;
    }

    public List<HeaderProperties> getHeaders() {
        return headers;
    }

    @Override
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public void setHeaders(List<HeaderProperties> headers) {
        this.headers = headers;
    }
}
