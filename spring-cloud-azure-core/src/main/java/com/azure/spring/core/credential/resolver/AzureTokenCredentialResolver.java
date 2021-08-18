package com.azure.spring.core.credential.resolver;

import com.azure.spring.core.credential.provider.AzureTokenCredentialProvider;
import com.azure.spring.core.properties.AzureProperties;

/**
 * Resolve the token credential according to the azure properties.
 */
public class AzureTokenCredentialResolver implements AzureCredentialResolver<AzureTokenCredentialProvider> {

    @Override
    public AzureTokenCredentialProvider resolve(AzureProperties properties) {
        // TODO: build token credential
        return new AzureTokenCredentialProvider(null);
    }

    /**
     * All SDKs will support this type.
     * @param properties Azure properties
     * @return Resolvable or not
     */
    @Override
    public boolean isResolvable(AzureProperties properties) {
        return true;
    }
}
