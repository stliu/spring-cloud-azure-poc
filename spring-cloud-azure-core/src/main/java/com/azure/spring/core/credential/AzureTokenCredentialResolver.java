package com.azure.spring.core.credential;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.properties.AzureProperties;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureTokenCredentialResolver implements AzureCredentialResolver<AzureCredential<TokenCredential>> {
    private AzureProperties azureProperties;

    public AzureTokenCredentialResolver(AzureProperties azureProperties) {
        this.azureProperties = azureProperties;
    }

    @Override
    public AzureCredential<TokenCredential> resolve() {
        // TODO
        TokenCredential tc = null;
        return new AzureCredential<TokenCredential>() {
            @Override
            public Class<TokenCredential> getType() {
                return TokenCredential.class;
            }

            @Override
            public TokenCredential getCredential() {
                return tc;
            }
        };
    }

}
