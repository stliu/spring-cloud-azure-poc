package com.azure.spring.core.credential.wrapper;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.properties.AzureProperties;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureTokenCredentialWrapper implements AzureCredential<TokenCredential> {

    private TokenCredential tokenCredential;

    public AzureTokenCredentialWrapper(TokenCredential tokenCredential) {
        this.tokenCredential = tokenCredential;
    }

    @Override
    public AzureCredentialType getType() {
        return AzureCredentialType.TOKEN_CREDENTIAL;
    }

    @Override
    public TokenCredential getCredential() {
        return this.tokenCredential;
    }
}
