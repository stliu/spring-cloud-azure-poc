package com.azure.spring.core.client.resolver;

import com.azure.spring.core.properties.ClientProperties;

/**
 * Resolver for Azure client options.
 * @param <T> Client options implementation
 */
public interface AzureClientOptionsResolver<T> {

    T resolve(ClientProperties properties);

    boolean isResolvable(ClientProperties properties);
}
