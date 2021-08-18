package com.azure.spring.autoconfigure.storage.credential;

import com.azure.spring.autoconfigure.storage.blob.AzureStorageBlobProperties;
import com.azure.spring.autoconfigure.storage.properties.AzureStorageProperties;
import com.azure.spring.core.credential.provider.AzureCredentialProvider;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.util.StringUtils;

/**
 * Resolve the storage shared key credential according to the {@link AzureStorageBlobProperties}.
 */
public class StorageSharedKeyCredentialResolver implements AzureCredentialResolver<AzureCredentialProvider<StorageSharedKeyCredential>> {

    @Override
    public AzureCredentialProvider<StorageSharedKeyCredential> resolve(AzureProperties azureProperties) {
        AzureStorageProperties properties = (AzureStorageProperties) azureProperties;
        if (!StringUtils.hasText(properties.getAccountName())
            || !StringUtils.hasText(properties.getAccountKey())) {
            return null;
        }

        return new AzureStorageSharedKeyCredentialProvider(properties.getAccountName(), properties.getAccountKey());
    }

    @Override
    public boolean isResolvable(AzureProperties azureProperties) {
        return azureProperties instanceof AzureStorageProperties;
    }
}
