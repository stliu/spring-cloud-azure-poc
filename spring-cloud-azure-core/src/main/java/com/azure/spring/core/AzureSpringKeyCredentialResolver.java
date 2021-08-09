package com.azure.spring.core;

/**
 * Resolve the key credential according IAzureKeyCredentialResolver implementation.
 * @param <T> Actual type of azure key credential
 */
public class AzureSpringKeyCredentialResolver<T> {

    public T resolve(IAzureKeyCredentialResolver<T> resolver) {
        return (T) new SpringKeyCredentialBuilder<T>().resolve(resolver).build();
    }
}
