package com.azure.spring.autoconfigure.core;

import com.azure.core.credential.TokenCredential;

public interface TokenCredentialAware {

    void setTokenCredential(TokenCredential credential);

}
