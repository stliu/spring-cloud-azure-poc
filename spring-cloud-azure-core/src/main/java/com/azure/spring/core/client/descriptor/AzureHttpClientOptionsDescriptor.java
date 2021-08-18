package com.azure.spring.core.client.descriptor;

import com.azure.core.util.HttpClientOptions;
import com.azure.spring.core.client.resolver.AzureClientOptionsResolver;
import com.azure.spring.core.client.resolver.AzureHttpClientOptionsResolver;

import java.util.function.Consumer;

public class AzureHttpClientOptionsDescriptor implements AzureClientOptionsDescriptor<HttpClientOptions> {

    private final Consumer<HttpClientOptions> consumer;

    public AzureHttpClientOptionsDescriptor(Consumer<HttpClientOptions> consumer) {
        this.consumer = consumer;
    }

    @Override
    public AzureClientOptionsResolver<HttpClientOptions> clientOptionsResolver() {
        return new AzureHttpClientOptionsResolver();
    }

    @Override
    public Consumer<HttpClientOptions> consumer() {
        return consumer;
    }
}
