package com.azure.spring.core.http;

import com.azure.core.http.policy.HttpPipelinePolicy;

import java.util.List;
import java.util.function.Supplier;

public interface HttpPipelinePoliciesSupplier extends Supplier<List<HttpPipelinePolicy>> {
}
