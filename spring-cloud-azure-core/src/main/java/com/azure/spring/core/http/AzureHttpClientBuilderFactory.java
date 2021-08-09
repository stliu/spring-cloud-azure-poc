package com.azure.spring.core.http;

import com.azure.spring.core.AzureServiceClientBuilderFactory;
import com.azure.spring.core.properties.HttpProperties;

/**
 * Common http client builder factory of module-based http protocol.
 * @param <T> Actual SDK service client builder
 */
public interface AzureHttpClientBuilderFactory<T> extends AzureServiceClientBuilderFactory<T>, HttpPipelineAware {

//    List<HttpPipelinePolicy> getHttpPipelinePolicies();

    default HttpProperties getRootHttpProperties() {
        return null;
    }

    default HttpProperties getInheritHttpProperties() {
        return null;
    }

    default HttpPipelinePoliciesSupplier getHttpPipelinePolicySupplier() {
        return new HttpPipelinePoliciesSupplierImpl(getRootHttpProperties(), getInheritHttpProperties(), supportFeatures());
    }

    default HttpClientSupplier getHttpClientSupplier() {
        return new HttpClientSupplierImpl(getRootHttpProperties(), getInheritHttpProperties());
    }
}