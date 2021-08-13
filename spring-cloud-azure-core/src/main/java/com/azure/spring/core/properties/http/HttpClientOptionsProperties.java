package com.azure.spring.core.properties.http;

import com.azure.spring.core.properties.ClientOptionsProperties;
import com.azure.spring.core.properties.ProxyProperties;

public class HttpClientOptionsProperties extends ClientOptionsProperties {

    private ProxyProperties proxy;

    private RequestRetryOptionsProperties retry;

//    private String endpoint;


    public ProxyProperties getProxy() {
        return proxy;
    }

    public void setProxy(ProxyProperties proxy) {
        this.proxy = proxy;
    }

    public RequestRetryOptionsProperties getRetry() {
        return retry;
    }

    public void setRetry(RequestRetryOptionsProperties retry) {
        this.retry = retry;
    }
}
