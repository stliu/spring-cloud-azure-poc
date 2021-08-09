package com.azure.spring.core.properties;

import com.azure.spring.core.AzureSpringHttpPolicyType;

import java.util.List;
import java.util.Map;

public class HttpProperties {

    public static final String PREFIX = "spring.cloud.azure.http";

    private HttpClientOptionsProperties client;

    private RetryProperties retry;

    private Map<AzureSpringHttpPolicyType, PipelinePolicyProperties> pipelinePolicies;

    private List<PipelinePolicyProperties> perCallPolicies;
    private List<PipelinePolicyProperties> perRetryPolicies;

    public List<PipelinePolicyProperties> getPerCallPolicies() {
        return perCallPolicies;
    }

    public void setPerCallPolicies(List<PipelinePolicyProperties> perCallPolicies) {
        this.perCallPolicies = perCallPolicies;
    }

    public List<PipelinePolicyProperties> getPerRetryPolicies() {
        return perRetryPolicies;
    }

    public void setPerRetryPolicies(List<PipelinePolicyProperties> perRetryPolicies) {
        this.perRetryPolicies = perRetryPolicies;
    }

    public Map<AzureSpringHttpPolicyType, PipelinePolicyProperties> getPipelinePolicies() {
        return pipelinePolicies;
    }

    public void setPipelinePolicies(Map<AzureSpringHttpPolicyType, PipelinePolicyProperties> pipelinePolicies) {
        this.pipelinePolicies = pipelinePolicies;
    }

    public HttpClientOptionsProperties getClient() {
        return client;
    }

    public void setClient(HttpClientOptionsProperties client) {
        this.client = client;
    }
}
