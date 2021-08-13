package com.azure.spring.core.builder;

/**
 * Azure SDK service client builder factory of all modules.
 * @param <T>
 */
public interface AzureServiceClientBuilderFactory<T> {

    T build();
}



