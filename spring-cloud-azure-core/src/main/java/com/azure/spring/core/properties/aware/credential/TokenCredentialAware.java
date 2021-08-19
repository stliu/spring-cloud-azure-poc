package com.azure.spring.core.properties.aware.credential;

import com.azure.core.credential.TokenCredential;

@FunctionalInterface
public interface TokenCredentialAware {
    void setTokenCredential(TokenCredential credential);
}
