package com.azure.spring.core.builder;

import com.azure.spring.core.aware.ProxyAware;
import com.azure.spring.core.aware.TokenCredentialAware;
import com.azure.spring.core.properties.AzureProperties;

/**
 * Azure SDK service client builder factory of all modules.
 * @param <T>
 */
public interface AzureServiceClientBuilderFactory<T> extends ProxyAware, TokenCredentialAware {

    T build();
}



