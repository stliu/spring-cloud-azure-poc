package com.azure.spring.core.builder;

import com.azure.core.util.ClientOptions;
import com.azure.spring.core.aware.HttpPropertiesAware;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.ClientProperties;
import com.azure.spring.core.properties.http.HttpClientProperties;
import com.azure.spring.core.properties.http.HttpProperties;

public abstract class AbstractAzureHttpClientBuilderFactory<T, O, R> extends AbstractAzureServiceClientBuilderFactory<T, O, R> {

    @Override
    protected void configureProxy(T builder) {
        // TODO:
    }

    /**
     * Override to return the http client properties
     * @return {@link ClientOptions}'s extended class {@link HttpClientProperties}
     */
    @Override
    protected ClientProperties getClientProperties() {
        AzureProperties azureProperties = getAzureProperties();
        if (azureProperties instanceof HttpPropertiesAware) {
            HttpProperties httpProperties = ((HttpPropertiesAware) azureProperties).getHttp();
            return httpProperties != null ? httpProperties.getClient() : null;
        }
        return null;
    }
}
