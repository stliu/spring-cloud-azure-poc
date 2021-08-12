package com.azure.spring.core.credential;

import com.azure.core.credential.AzureSasCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.properties.AzureProperties;

/**
 * Resolve the sas token credential according azure properties.
 */
public class AzureSasCredentialResolver implements AzureCredentialResolver<AzureCredential<AzureSasCredential>> {

    private String sasToken;

    public AzureSasCredentialResolver(String sasToken) {
        this.sasToken = sasToken;
    }

    @Override
    public AzureCredential<AzureSasCredential> resolve() {
        // TODO
        AzureSasCredential sasCredential = new AzureSasCredential(sasToken);
        return new AzureCredential<AzureSasCredential>() {
            @Override
            public Class<AzureSasCredential> getType() {
                return AzureSasCredential.class;
            }

            @Override
            public AzureSasCredential getCredential() {
                return sasCredential;
            }
        };
    }

}
