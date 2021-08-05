package com.azure.spring.core;

import com.azure.spring.core.identify.AzureServiceFeature;

import java.util.List;

/**
 * Wrapper the Azure SDK service client builder.
 * @param <T>
 */
public interface AzureServiceClientBuilderFactory<T> {

    T build();

    List<AzureServiceFeature> supportFeatures();
}



