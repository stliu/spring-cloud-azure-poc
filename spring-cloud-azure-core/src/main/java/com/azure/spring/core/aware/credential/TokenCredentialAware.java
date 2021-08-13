package com.azure.spring.core.aware.credential;

import com.azure.core.credential.TokenCredential;

@FunctionalInterface
public interface TokenCredentialAware {
    void setTokenCredential(TokenCredential credential);
}
