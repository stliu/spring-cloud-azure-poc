package com.azure.spring.core;

@FunctionalInterface
public interface ServiceEndpointProvider {
    String getEndpoint();
}
