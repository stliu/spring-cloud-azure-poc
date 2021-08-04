package com.azure.spring.core;

@FunctionalInterface
public interface ServiceClientBuilderCustomizer<BUILDER> {

    void customize(BUILDER builder);
}
