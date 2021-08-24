package com.azure.spring.core.credential.provider;

import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;

public class AzureRawTokenCredentialProvider implements AzureCredentialProvider<TokenCredentialProperties> {

    public static AzureCredentialType RAW_TOKEN_CREDENTIAL = new AzureCredentialType("raw_token");

    public TokenCredentialProperties tokenCredentialProperties;

    public AzureRawTokenCredentialProvider(TokenCredentialProperties properties) {
        this.tokenCredentialProperties = properties;
    }

    @Override
    public AzureCredentialType getType() {
        return RAW_TOKEN_CREDENTIAL;
    }

    @Override
    public TokenCredentialProperties getCredential() {
        return this.tokenCredentialProperties;
    }
}
