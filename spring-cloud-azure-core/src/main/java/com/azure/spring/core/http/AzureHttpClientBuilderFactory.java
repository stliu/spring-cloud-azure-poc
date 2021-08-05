package com.azure.spring.core.http;

import com.azure.spring.core.AzureServiceClientBuilderFactory;

public interface AzureHttpClientBuilderFactory<T> extends AzureServiceClientBuilderFactory<T>, HttpPipelineAware {

    default HttpPipelinePoliciesSupplier getHttpPipelinePolicySupplier() {
        return new HttpPipelinePoliciesSupplierImpl();
    }
}