package com.azure.spring.core.aware.credential;

public interface KeyAware {

    void setKey(String key);

    String getKey();
}
