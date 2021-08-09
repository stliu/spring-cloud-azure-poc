package com.azure.spring.core.properties;

import com.azure.core.http.HttpPipelinePosition;
import com.azure.spring.core.AzureSpringHttpPolicyType;
import com.azure.spring.core.identify.AzureServiceFeature;

import java.util.Map;

public class PipelinePolicyProperties {

    private AzureServiceFeature feature;
    private HttpPipelinePosition httpPipelinePosition;
    private AzureSpringHttpPolicyType policyType;
    private Map<String, Object> additionalParameters;

    public AzureServiceFeature getFeature() {
        return feature;
    }

    public void setFeature(AzureServiceFeature feature) {
        this.feature = feature;
    }

    public AzureSpringHttpPolicyType getPolicyType() {
        return policyType;
    }

    public void setPolicyType(AzureSpringHttpPolicyType policyType) {
        this.policyType = policyType;
    }

    public Map<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(Map<String, Object> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }

    public HttpPipelinePosition getHttpPipelinePosition() {
        return httpPipelinePosition;
    }

    public void setHttpPipelinePosition(HttpPipelinePosition httpPipelinePosition) {
        this.httpPipelinePosition = httpPipelinePosition;
    }
}
