package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.util.HttpClientOptions;
import com.azure.spring.autoconfigure.storage.credential.StorageSharedKeyAuthenticationDescriptor;
import com.azure.spring.core.builder.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.client.descriptor.AzureClientOptionsDescriptor;
import com.azure.spring.core.client.descriptor.AzureHttpClientOptionsDescriptor;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.SasAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.TokenAuthenticationDescriptor;
import com.azure.spring.core.credential.resolver.AzureCredentialResolvers;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.common.policy.RequestRetryOptions;
import org.springframework.boot.context.properties.PropertyMapper;

import java.util.Arrays;
import java.util.List;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client according the configuration context
 * and blob properties.
 */
public class AzureBlobClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<BlobClientBuilder, HttpClientOptions, RequestRetryOptions> {

    private final AzureStorageBlobProperties blobProperties;

    private AzureCredentialResolvers credentialResolvers;

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
        if (blobProperties.getAnonymousAccess()) {
            builder.setAnonymousAccess();
        }
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return blobProperties;
    }

    @Override
    protected List<AuthenticationDescriptor> getAuthenticationDescriptors(BlobClientBuilder builder) {
        return Arrays.asList(
            new StorageSharedKeyAuthenticationDescriptor(c ->
                builder.credential(c.getCredential())),
            new TokenAuthenticationDescriptor(c ->
                builder.credential(c.getCredential())),
            new SasAuthenticationDescriptor(c ->
                builder.credential(c.getCredential())));
    }

    @Override
    protected AzureClientOptionsDescriptor<HttpClientOptions> getAzureClientOptionsDescriptor(BlobClientBuilder builder) {
        return new AzureHttpClientOptionsDescriptor(client -> builder.clientOptions(client));
    }
}
