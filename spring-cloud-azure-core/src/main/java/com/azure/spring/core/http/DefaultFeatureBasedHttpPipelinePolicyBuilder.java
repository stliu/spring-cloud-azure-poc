package com.azure.spring.core.http;

import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.spring.core.properties.HttpProperties;
import com.azure.spring.core.properties.PipelinePolicyProperties;

/**
 * Default http pipeline policy builder.
 */
public class DefaultFeatureBasedHttpPipelinePolicyBuilder implements FeatureBasedHttpPipelinePolicyBuilder {

    HttpPipelinePolicy policy;
    private final HttpProperties properties;

    private PipelinePolicyProperties policyProperties;

    public DefaultFeatureBasedHttpPipelinePolicyBuilder(HttpProperties properties){
        this.properties = properties;
    }

    public DefaultFeatureBasedHttpPipelinePolicyBuilder policyProperties(PipelinePolicyProperties policyProperties){
        this.policyProperties = policyProperties;
        return this;
    }

    @Override
    public HttpPipelinePolicy build() {
        // TODO: Create the HttpPipelinePolicy according the user properties
        return policy;
    }
}
