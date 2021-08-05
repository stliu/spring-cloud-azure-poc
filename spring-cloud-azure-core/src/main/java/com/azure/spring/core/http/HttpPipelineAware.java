package com.azure.spring.core.http;

import com.azure.core.http.HttpPipeline;

public interface HttpPipelineAware {

    void setPipeline(HttpPipeline pipeline);
}
