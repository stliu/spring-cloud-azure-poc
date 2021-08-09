package com.azure.spring.core;

import com.azure.spring.core.identify.AzureServiceFeature;

import java.util.List;

/**
 * Azure SDK service client builder factory of all modules.
 * @param <T>
 */
public interface AzureServiceClientBuilderFactory<T> {

    T build();

    List<AzureServiceFeature> supportFeatures();
}



