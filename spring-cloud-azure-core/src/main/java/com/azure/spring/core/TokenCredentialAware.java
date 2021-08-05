package com.azure.spring.core;

import com.azure.core.credential.TokenCredential;

@FunctionalInterface
public interface TokenCredentialAware {
    void setTokenCredential(TokenCredential credential);
}
