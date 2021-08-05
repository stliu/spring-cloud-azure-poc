package com.azure.spring.core;

/**
 * Resolve the key credential according IAzureKeyCredentialResolver implementation.
 */
public class AzureSpringKeyCredentialResolver<T> {

    public T resolve(IAzureKeyCredentialResolver<T> resolver) {
        return (T) new SpringKeyCredentialBuilder<T>().resolve(resolver).build();
    }
}
