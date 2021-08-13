package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.credential.TokenCredential;
import com.azure.core.util.Configuration;
import com.azure.spring.core.builder.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.context.AzureSpringHttpConfigurationContext;
import com.azure.spring.core.credential.AzureCredentialManager;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.ClientOptionsProperties;
import com.azure.spring.core.properties.credential.NamedKeyProperties;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.storage.blob.BlobClientBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client according the configuration context
 * and blob properties.
 */
public class AzureBlobClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<BlobClientBuilder> {

    private final AzureSpringHttpConfigurationContext configurationContext;
    private final AzureStorageBlob blobProperties;

    public AzureBlobClientBuilderFactory(AzureSpringHttpConfigurationContext configurationContext,
                                         AzureStorageBlob blobProperties) {
        this.configurationContext = configurationContext;
        this.blobProperties = blobProperties;
    }

    @Override
    public BlobClientBuilder createBuilderInstance() {
        return new BlobClientBuilder();
    }

    @Override
    public void configureService(BlobClientBuilder builder) {
        Optional.ofNullable(blobProperties.getCustomerProvidedKey()).ifPresent(builder::customerProvidedKey);
        Optional.ofNullable(blobProperties.getEncryptionScope()).ifPresent(builder::encryptionScope);

        // TODO: wrapper credential
        Optional.ofNullable(blobProperties.getConnectionString()).ifPresent(builder::connectionString);

        Optional.ofNullable(blobProperties.getEndpoint()).ifPresent(builder::endpoint);
        Optional.ofNullable(blobProperties.getBlobName()).ifPresent(builder::blobName);
        Optional.ofNullable(blobProperties.getContainerName()).ifPresent(builder::containerName);
        Optional.ofNullable(blobProperties.getSnapshot()).ifPresent(builder::snapshot);
        Optional.ofNullable(blobProperties.getVersionId()).ifPresent(builder::versionId);
        Optional.ofNullable(blobProperties.getServiceVersion()).ifPresent(builder::serviceVersion);

        if (blobProperties.getAnonymousAccess()) {
            builder.setAnonymousAccess();
        }
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return blobProperties;
    }

    @Override
    protected List<AzureCredentialType> getOrderedSupportCredentialTypes() {
        return Arrays.asList(AzureCredentialType.TOKEN_CREDENTIAL);
    }

    @Override
    protected AzureCredentialManager getAzureCredentialManager(BlobClientBuilder builder) {
        AzureCredentialManager azureCredentialManager = new AzureCredentialManager();
        azureCredentialManager.registerConsumer(AzureCredentialType.TOKEN_CREDENTIAL,
            c -> builder.credential((TokenCredential) c.getCredential()));
        return azureCredentialManager;
    }
}
