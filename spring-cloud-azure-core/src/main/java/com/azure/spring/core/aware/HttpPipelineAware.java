package com.azure.spring.core.aware;

import com.azure.core.http.HttpPipeline;

public interface HttpPipelineAware {

    void setPipeline(HttpPipeline pipeline);
}
