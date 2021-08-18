package com.azure.spring.core.properties.http;

import com.azure.spring.core.properties.ClientProperties;
import com.azure.spring.core.properties.ProxyProperties;

public class HttpClientProperties extends ClientProperties {

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
