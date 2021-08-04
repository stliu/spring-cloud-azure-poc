package com.azure.spring.core.http;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpClientProvider;
import com.azure.core.util.HttpClientOptions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AzureHttpClientFactoryBean extends AbstractFactoryBean<HttpClient> implements ApplicationContextAware {

    private HttpClientProvider httpClientProvider;
    private HttpClientOptions httpClientOptions;
    private ApplicationContext applicationContext;

    private boolean isSingleton = true;

    public AzureHttpClientFactoryBean() {
    }

    @Override
    public Class<?> getObjectType() {
        return HttpClient.class;
    }

    @Override
    protected HttpClient createInstance() throws Exception {

        if (httpClientOptions == null) {
            return httpClientProvider.createInstance();
        } else {
            return httpClientProvider.createInstance(httpClientOptions);
        }
    }

    @Override
    public void setSingleton(boolean singleton) {
        isSingleton = singleton;
    }

    @Override
    public boolean isSingleton() {
        return isSingleton;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setHttpClientProvider(HttpClientProvider httpClientProvider) {
        this.httpClientProvider = httpClientProvider;
    }

    public void setHttpClientOptions(HttpClientOptions httpClientOptions) {
        this.httpClientOptions = httpClientOptions;
    }
}
