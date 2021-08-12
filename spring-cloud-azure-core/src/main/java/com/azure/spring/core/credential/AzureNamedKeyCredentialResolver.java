package com.azure.spring.core.credential;

import com.azure.core.credential.AzureNamedKeyCredential;
import com.azure.spring.core.properties.NamedKeyCredentialProperties;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureNamedKeyCredentialResolver implements AzureCredentialResolver<AzureCredential<AzureNamedKeyCredential>> {

    private NamedKeyCredentialProperties properties;

    public AzureNamedKeyCredentialResolver(NamedKeyCredentialProperties properties) {
        this.properties = properties;
    }

    @Override
    public AzureCredential<AzureNamedKeyCredential> resolve() {
        AzureNamedKeyCredential credential = new AzureNamedKeyCredential(properties.getName(), properties.getKey());
        return new AzureCredential<AzureNamedKeyCredential>() {
            @Override
            public Class<AzureNamedKeyCredential> getType() {
                return AzureNamedKeyCredential.class;
            }

            @Override
            public AzureNamedKeyCredential getCredential() {
                return credential;
            }
        };
    }

}
