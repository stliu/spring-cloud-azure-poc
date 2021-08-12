package com.azure.spring.core;

import com.azure.spring.core.credential.AzureCredentialResolver;

/**
 * Interface to resolve the Azure Key Credential
 * @param <T> Actual type of azure key credential
 */
public interface IAzureKeyCredentialResolver<T> extends AzureCredentialResolver<T> {
}
