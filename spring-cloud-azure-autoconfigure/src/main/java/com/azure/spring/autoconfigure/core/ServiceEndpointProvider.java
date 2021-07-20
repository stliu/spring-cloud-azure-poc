package com.azure.spring.autoconfigure.core;

@FunctionalInterface
public interface ServiceEndpointProvider {
    String getEndpoint();
}
