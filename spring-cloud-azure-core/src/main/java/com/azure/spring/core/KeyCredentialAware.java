package com.azure.spring.core;

@FunctionalInterface
public interface KeyCredentialAware<T> {
    void setKeyCredential(T springKeyCredential);
}
