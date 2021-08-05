package com.azure.spring.core.http;

import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.spring.core.identify.AzureServiceFeature;

import java.util.List;

/**
 * Default function to supply the HttpPipelinePolicy list.
 */
public class HttpPipelinePoliciesSupplierImpl implements HttpPipelinePoliciesSupplier {

    private final HttpProperties properties;
    private final List<AzureServiceFeature> features;

    public HttpPipelinePoliciesSupplierImpl(HttpProperties properties, List<AzureServiceFeature> features) {
        this.properties = properties;
        this.features = features;
    }

    @Override
    public List<HttpPipelinePolicy> get() {
        return null;
    }
}
