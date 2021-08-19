package com.azure.spring.core.factory;

import com.azure.core.http.HttpClient;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.SasAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.TokenAuthenticationDescriptor;
import com.azure.spring.core.credential.provider.AzureCredentialProvider;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.AzureStorageBlobProperties;
import com.azure.spring.core.properties.AzureStorageProperties;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.azure.spring.core.factory.AzureStorageBlobClientBuilderFactory.StorageSharedKeyAuthenticationDescriptor.STORAGE_SHARED_KEY;

/**
 * @author Xiaolu Dai, 2021/8/19.
 */
public class AzureStorageBlobClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<BlobClientBuilder> {


    private AzureStorageBlobProperties blobProperties;

    public AzureStorageBlobClientBuilderFactory(AzureStorageBlobProperties blobProperties) {
        this.blobProperties = blobProperties;
    }

    @Override
    protected void configureHttpClient(BlobClientBuilder builder, HttpClient httpClient) {
        builder.httpClient(httpClient);
    }

    @Override
    protected BlobClientBuilder createBuilderInstance() {
        return new BlobClientBuilder();
    }

    @Override
    protected void configureRetry(BlobClientBuilder builder) {

    }

    @Override
    protected void configureService(BlobClientBuilder builder) {
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
    protected AzureProperties getAzureProperties() {
        return this.blobProperties;
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(BlobClientBuilder builder) {
        return Arrays.asList(
            new StorageSharedKeyAuthenticationDescriptor(c -> builder.credential(c.getCredential())),
            new TokenAuthenticationDescriptor(c -> builder.credential(c.getCredential())),
            new SasAuthenticationDescriptor(c -> builder.credential(c.getCredential())));
    }

    public static class StorageSharedKeyAuthenticationDescriptor implements AuthenticationDescriptor<StorageSharedKeyCredentialProvider> {

        static final AzureCredentialType STORAGE_SHARED_KEY = new AzureCredentialType("storage_shared_key");

        private final Consumer<StorageSharedKeyCredentialProvider> consumer;

        public StorageSharedKeyAuthenticationDescriptor(Consumer<StorageSharedKeyCredentialProvider> consumer) {
            this.consumer = consumer;
        }

        @Override
        public AzureCredentialType azureCredentialType() {
            return STORAGE_SHARED_KEY;
        }

        @Override
        public AzureCredentialResolver<StorageSharedKeyCredentialProvider> azureCredentialResolver() {
            return new StorageSharedKeyCredentialResolver();
        }

        @Override
        public Consumer<StorageSharedKeyCredentialProvider> consumer() {
            return consumer;
        }
    }

    public static class StorageSharedKeyCredentialProvider implements AzureCredentialProvider<StorageSharedKeyCredential> {

        private final String accountName;
        private final String accountKey;

        public StorageSharedKeyCredentialProvider(String accountName,
                                                  String accountKey) {
            this.accountName = accountName;
            this.accountKey = accountKey;
        }

        @Override
        public AzureCredentialType getType() {
            return STORAGE_SHARED_KEY;
        }

        @Override
        public StorageSharedKeyCredential getCredential() {
            return new StorageSharedKeyCredential(accountName, accountKey);
        }
    }

    public static class StorageSharedKeyCredentialResolver implements AzureCredentialResolver<StorageSharedKeyCredentialProvider> {

        @Override
        public StorageSharedKeyCredentialProvider resolve(AzureProperties azureProperties) {
            if (!isResolvable(azureProperties)) {
                return null;
            }

            AzureStorageProperties properties = (AzureStorageProperties) azureProperties;
            if (!StringUtils.hasText(properties.getAccountName())
                    || !StringUtils.hasText(properties.getAccountKey())) {
                return null;
            }

            return new StorageSharedKeyCredentialProvider(properties.getAccountName(), properties.getAccountKey());
        }

        @Override
        public boolean isResolvable(AzureProperties azureProperties) {
            return azureProperties instanceof AzureStorageProperties;
        }
    }
}
