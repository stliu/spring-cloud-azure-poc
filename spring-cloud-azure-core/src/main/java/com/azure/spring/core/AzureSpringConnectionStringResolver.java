package com.azure.spring.core;

/**
 * Resolve the connection string according ISpringConnectionStringResolver implementation.
 * @param <T> Actual type of connection string
 */
public class AzureSpringConnectionStringResolver<T> {
    public T resolve(ISpringConnectionStringResolver<T> resolver) {
        return (T) new SpringConnectionStringBuilder<T>().resolve(resolver).build();
    }
}