package com.azure.spring.autoconfigure.storage.credential;

import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.storage.common.StorageSharedKeyCredential;

import java.util.function.Consumer;

public class StorageSharedKeyAuthenticationDescriptor implements AuthenticationDescriptor<AzureStorageSharedKeyCredentialProvider, StorageSharedKeyCredential> {

    protected static final AzureCredentialType STORAGE_SHARED_KEY = new AzureCredentialType("storage_shared_key");
    private final Consumer<AzureStorageSharedKeyCredentialProvider> consumer;

    public StorageSharedKeyAuthenticationDescriptor(Consumer<AzureStorageSharedKeyCredentialProvider> consumer) {
        this.consumer = consumer;
    }

    @Override
    public AzureCredentialType azureCredentialType() {
        return STORAGE_SHARED_KEY;
    }

    @Override
    public AzureCredentialResolver azureCredentialResolver() {
        return new StorageSharedKeyCredentialResolver();
    }

    @Override
    public Consumer<AzureStorageSharedKeyCredentialProvider> consumer() {
        return consumer;
    }
}
