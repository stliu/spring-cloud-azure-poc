package com.azure.spring.autoconfigure.core;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpClientProvider;

public class TestHttpClientProvider implements HttpClientProvider {

    @Override
    public HttpClient createInstance() {
        return new TestHttpClient();
    }
}
    