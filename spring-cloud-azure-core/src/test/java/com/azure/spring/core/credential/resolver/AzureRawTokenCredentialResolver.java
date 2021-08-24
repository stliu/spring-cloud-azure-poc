package com.azure.spring.core.credential.resolver;


import com.azure.spring.core.credential.provider.AzureRawTokenCredentialProvider;
import com.azure.spring.core.properties.AzureProperties;

public class AzureRawTokenCredentialResolver implements AzureCredentialResolver<AzureRawTokenCredentialProvider> {

    @Override
    public AzureRawTokenCredentialProvider resolve(AzureProperties properties) {
        if (properties.getCredential() == null) {
            return null;
        }
        return new AzureRawTokenCredentialProvider(properties.getCredential());
    }

    @Override
    public boolean isResolvable(AzureProperties properties) {
        return true;
    }
}
