package com.azure.spring.core;

import com.azure.spring.core.credential.AzureCredentialResolver;

/**
 * Interface to resolve the connection string
 * @param <T> Actual type of connection string
 */
public interface ISpringConnectionStringResolver<T> extends AzureCredentialResolver<T> {
}
