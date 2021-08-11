package com.azure.spring.core.properties;

import com.azure.spring.core.properties.http.HeaderProperties;

import java.util.List;

public class ClientOptionsProperties {

    private String applicationId;

    private List<HeaderProperties> headers;

    public String getApplicationId() {
        return applicationId;
    }

    public List<HeaderProperties> getHeaders() {
        return headers;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setHeaders(List<HeaderProperties> headers) {
        this.headers = headers;
    }
}
