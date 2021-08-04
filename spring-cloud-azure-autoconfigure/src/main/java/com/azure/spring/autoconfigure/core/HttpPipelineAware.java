package com.azure.spring.autoconfigure.core;

import com.azure.core.http.HttpPipeline;

public interface HttpPipelineAware {

    void setPipeline(HttpPipeline pipeline);
}
