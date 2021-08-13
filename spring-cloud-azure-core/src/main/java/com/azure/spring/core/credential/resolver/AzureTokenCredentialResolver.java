package com.azure.spring.core.credential.resolver;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.wrapper.AzureTokenCredentialWrapper;
import com.azure.spring.core.properties.AzureProperties;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureTokenCredentialResolver implements AzureCredentialResolver<AzureCredential<TokenCredential>> {

    @Override
    public AzureCredential<TokenCredential> resolve(AzureProperties azureProperties) {
        // TODO
        return new AzureTokenCredentialWrapper(null);
    }

    @Override
    public AzureCredentialType support() {
        return AzureCredentialType.TOKEN_CREDENTIAL;
    }
}
