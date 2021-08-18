package com.azure.spring.core.client.descriptor;

import com.azure.spring.core.client.resolver.AzureClientOptionsResolver;

import java.util.function.Consumer;

/**
 * Describe the azure client options resolver and consumer.
 */
public interface AzureClientOptionsDescriptor<T> {

    AzureClientOptionsResolver<T> clientOptionsResolver();

    Consumer<T> consumer();
}
