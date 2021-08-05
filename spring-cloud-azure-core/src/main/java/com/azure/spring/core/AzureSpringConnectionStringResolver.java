package com.azure.spring.core;

/**
 * Resolve the connection string according ISpringConnectionStringResolver implementation.
 */
public class AzureSpringConnectionStringResolver<T> {
    public T resolve(ISpringConnectionStringResolver<T> resolver) {
        return (T) new SpringConnectionStringBuilder().resolve(resolver).build();
    }
}