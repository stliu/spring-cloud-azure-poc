package com.azure.spring.service.factory;

import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.http.HttpProperties;

public class IdentifyClientProperties extends AzureProperties {

    private HttpProperties http;

    private boolean isSharedTokenCacheCred;

    public HttpProperties getHttp() {
        return http;
    }

    public void setHttp(HttpProperties http) {
        this.http = http;
    }

    public boolean isSharedTokenCacheCred() {
        return isSharedTokenCacheCred;
    }

    public void setSharedTokenCacheCred(boolean sharedTokenCacheCred) {
        isSharedTokenCacheCred = sharedTokenCacheCred;
    }
}
