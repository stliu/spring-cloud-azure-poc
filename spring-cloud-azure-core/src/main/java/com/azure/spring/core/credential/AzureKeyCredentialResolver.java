package com.azure.spring.core.credential;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.spring.core.properties.AzureProperties;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureKeyCredentialResolver implements AzureCredentialResolver<AzureCredential<AzureKeyCredential>> {

    private String key;

    public AzureKeyCredentialResolver(String key) {
        this.key = key;
    }

    @Override
    public AzureCredential<AzureKeyCredential> resolve() {
        AzureKeyCredential keyCredential = new AzureKeyCredential(key);
        return new AzureCredential<AzureKeyCredential>() {
            @Override
            public Class<AzureKeyCredential> getType() {
                return AzureKeyCredential.class;
            }

            @Override
            public AzureKeyCredential getCredential() {
                return keyCredential;
            }
        };
    }

}
