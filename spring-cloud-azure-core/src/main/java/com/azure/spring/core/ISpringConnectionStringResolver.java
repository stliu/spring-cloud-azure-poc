package com.azure.spring.core;

/**
 * Interface to resolve the connection string
 * @param <T> Actual type of connection string
 */
public interface ISpringConnectionStringResolver<T> extends ISpringCredentialResolver<T> {
}
