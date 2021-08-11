package com.azure.spring.core.properties.http;

import com.azure.spring.core.properties.ClientOptionsProperties;

public class HttpClientOptionsProperties extends ClientOptionsProperties {

    private HttpProxyOptionsProperties proxy;

    private RequestRetryOptionsProperties retry;

//    private String endpoint;


    public HttpProxyOptionsProperties getProxy() {
        return proxy;
    }

    public void setProxy(HttpProxyOptionsProperties proxy) {
        this.proxy = proxy;
    }

    public RequestRetryOptionsProperties getRetry() {
        return retry;
    }

    public void setRetry(RequestRetryOptionsProperties retry) {
        this.retry = retry;
    }
}
