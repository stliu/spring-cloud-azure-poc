package com.azure.spring.core.http;

import com.azure.core.http.HttpClient;

/**
 * Default function to supply the HttpClient.
 */
public class HttpClientSupplierImpl implements HttpClientSupplier {

    private final HttpProperties properties;
    public HttpClientSupplierImpl(HttpProperties properties) {
        this.properties = properties;
    }
    @Override
    public HttpClient get() {
        return HttpClient.createDefault();
    }
}
