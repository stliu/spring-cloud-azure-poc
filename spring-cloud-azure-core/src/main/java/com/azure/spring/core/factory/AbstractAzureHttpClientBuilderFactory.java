package com.azure.spring.core.factory;

import com.azure.core.http.HttpClient;
import com.azure.core.util.Header;
import com.azure.spring.core.properties.client.HttpClientProperties;

import java.util.List;

public abstract class AbstractAzureHttpClientBuilderFactory<T> extends AbstractAzureServiceClientBuilderFactory<T> {

    protected HttpClientFactory httpClientFactory = new HttpClientFactory();

    protected abstract void configureHttpClient(T builder, HttpClient httpClient);


    @Override
    protected void configureCore(T builder) {
        configureRetry(builder);
        configureCredential(builder);
        configureClient(builder);
    }

    @Override
    protected void configureProxy(T builder) {
        httpClientFactory.configureProxy(getAzureProperties().getProxy());
    }

    @Override
    protected void configureRetry(T builder) {

    }

    @Override
    protected void configureClient(T builder) {
        configureApplicationId(builder, getApplicationId());
        configureHeaders(builder, getHeaders());
        configureProxy(builder);
        configureHttpProperties(builder);
        configureHttpClient(builder, createHttpClientInstance());
    }

    protected void configureHttpProperties(T builder) {
        final HttpClientProperties client = (HttpClientProperties) getAzureProperties().getClient();
        if (client == null) {
            return;
        }
        this.httpClientFactory.configureReadTimeout(client.getReadTimeout());
        this.httpClientFactory.configureWriteTimeout(client.getWriteTimeout());
        this.httpClientFactory.configureResponseTimeout(client.getResponseTimeout());
    }

    @Override
    protected void configureApplicationId(T builder, String applicationId) {
        this.httpClientFactory.configureApplicationId(applicationId);
    }

    @Override
    protected void configureHeaders(T builder, List<Header> headers) {
        this.httpClientFactory.configureHttpHeaders(headers);
    }

    protected HttpClient createHttpClientInstance() {
        return httpClientFactory.build();
    }

}
