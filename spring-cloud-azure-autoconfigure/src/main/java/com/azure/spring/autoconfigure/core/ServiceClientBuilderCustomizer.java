package com.azure.spring.autoconfigure.core;

@FunctionalInterface
public interface ServiceClientBuilderCustomizer<T extends AzureServiceClientBuilder<?>> {

    void customize(T builder);
}
