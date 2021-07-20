package com.azure.spring.autoconfigure.core;

@FunctionalInterface
public interface ServiceClientBuilderCustomizer<BUILDER> {

    void customize(BUILDER builder);
}
