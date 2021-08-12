package com.azure.spring.core.aware;

@FunctionalInterface
public interface KeyCredentialAware<T> {
    void setKeyCredential(T springKeyCredential);
}
