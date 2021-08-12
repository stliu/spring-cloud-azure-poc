package com.azure.spring.core.aware;

import com.azure.core.credential.TokenCredential;

@FunctionalInterface
public interface TokenCredentialAware {
    void setTokenCredential(TokenCredential credential);
}
