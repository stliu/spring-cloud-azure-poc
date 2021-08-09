package com.azure.spring.core;

/**
 * Interface to resolve the Azure Key Credential
 * @param <T> Actual type of azure key credential
 */
public interface IAzureKeyCredentialResolver<T> extends ISpringCredentialResolver<T> {
}
