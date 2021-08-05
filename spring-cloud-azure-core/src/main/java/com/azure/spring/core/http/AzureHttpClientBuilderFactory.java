package com.azure.spring.core.http;

import com.azure.spring.core.AzureServiceClientBuilderFactory;

public interface AzureHttpClientBuilderFactory<T> extends AzureServiceClientBuilderFactory<T> {

    default HttpProperties getHttpProperties() {
        return null;
    }

    default HttpPipelinePoliciesSupplier getHttpPipelinePolicySupplier() {
        return new HttpPipelinePoliciesSupplierImpl(getHttpProperties(), supportFeatures());
    }

    default HttpClientSupplier getHttpClientSupplier() {
        return new HttpClientSupplierImpl(getHttpProperties());
    }
}