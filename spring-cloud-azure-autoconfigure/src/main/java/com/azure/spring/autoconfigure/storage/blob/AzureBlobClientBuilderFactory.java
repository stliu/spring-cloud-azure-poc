package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.util.Configuration;
import com.azure.spring.autoconfigure.storage.common.credential.StorageSharedKeyAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.ConnectionStringAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.SasAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.TokenAuthenticationDescriptor;
import com.azure.spring.core.factory.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.boot.context.properties.PropertyMapper;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client according the configuration context
 * and blob properties.
 */
public class AzureBlobClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<BlobClientBuilder> {

    private final AzureStorageBlobProperties blobProperties;

    public AzureBlobClientBuilderFactory(AzureStorageBlobProperties blobProperties) {
        this.blobProperties = blobProperties;
    }

    @Override
    public BlobClientBuilder createBuilderInstance() {
        return new BlobClientBuilder();
    }

    @Override
    public void configureService(BlobClientBuilder builder) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(blobProperties.getCustomerProvidedKey()).to(builder::customerProvidedKey);
        map.from(blobProperties.getEncryptionScope()).to(builder::encryptionScope);
        map.from(blobProperties.getEndpoint()).to(builder::endpoint);
        map.from(blobProperties.getBlobName()).to(builder::blobName);
        map.from(blobProperties.getContainerName()).to(builder::containerName);
        map.from(blobProperties.getSnapshot()).to(builder::snapshot);
        map.from(blobProperties.getVersionId()).to(builder::versionId);
        map.from(blobProperties.getServiceVersion()).to(builder::serviceVersion);
        // Only storage blob has anonymous access feature.
        // TODO: Add anonymous mechanism
        if (blobProperties.isAnonymousAccess()) {
            builder.setAnonymousAccess();
        }
    }

    @Override
    protected BiConsumer<BlobClientBuilder, HttpClient> consumeHttpClient() {
        return BlobClientBuilder::httpClient;
    }

    @Override
    protected BiConsumer<BlobClientBuilder, HttpPipelinePolicy> consumeHttpPipelinePolicy() {
        return BlobClientBuilder::addPolicy;
    }

    @Override
    protected BiConsumer<BlobClientBuilder, Configuration> consumeConfiguration() {
        return BlobClientBuilder::configuration;
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return blobProperties;
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(BlobClientBuilder builder) {
        return Arrays.asList(
            new ConnectionStringAuthenticationDescriptor(provider -> builder.connectionString(provider.getCredential())),
            new StorageSharedKeyAuthenticationDescriptor(provider -> builder.credential(provider.getCredential())),
            new SasAuthenticationDescriptor(provider -> builder.credential(provider.getCredential())),
            new TokenAuthenticationDescriptor(provider -> builder.credential(provider.getCredential()))
        );
    }

    @Override
    public void addHttpPipelinePolicy(HttpPipelinePolicy policy) {
        super.addHttpPipelinePolicy(policy);
    }
}
