package com.azure.spring.core.http;

import com.azure.core.http.policy.HttpPipelinePolicy;

public interface HttpPipelinePolicyBuilder {

    HttpPipelinePolicy build();
}
