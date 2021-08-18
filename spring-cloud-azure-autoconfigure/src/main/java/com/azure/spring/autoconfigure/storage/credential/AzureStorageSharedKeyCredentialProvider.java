package com.azure.spring.autoconfigure.storage.credential;

import com.azure.spring.core.credential.provider.AzureCredentialProvider;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.storage.common.StorageSharedKeyCredential;

/**
 * Provide the azure storage shared key credential.
 */
public class AzureStorageSharedKeyCredentialProvider implements AzureCredentialProvider<StorageSharedKeyCredential> {

    private final String accountName;
    private final String accountKey;

    public AzureStorageSharedKeyCredentialProvider(String accountName,
                                                   String accountKey) {
        this.accountName = accountName;
        this.accountKey = accountKey;
    }

    @Override
    public AzureCredentialType getType() {
        return AzureCredentialType.SAS_CREDENTIAL;
    }

    @Override
    public StorageSharedKeyCredential getCredential() {
        return new StorageSharedKeyCredential(accountName, accountKey);
    }
}
