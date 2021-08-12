package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.spring.core.builder.AzureHttpClientBuilderFactory;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.storage.blob.BlobClientBuilder;

import java.util.List;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client according the configuration context
 * and blob properties.
 */
public class AzureBlobClientBuilderFactory implements
    AzureHttpClientBuilderFactory<BlobClientBuilder> {

    private final AzureSpringHttpConfigurationContext configurationContext;
    private final AzureStorageBlob blobProperties;
    private final List<HttpPipelinePolicy> policies;

    private BlobClientBuilder builder;
    private HttpPipeline pipeline;

    public AzureBlobClientBuilderFactory(AzureSpringHttpConfigurationContext configurationContext,
                                         AzureStorageBlob blobProperties,
                                         List<HttpPipelinePolicy> policies) {
        this.configurationContext = configurationContext;
        this.blobProperties = blobProperties;
        this.policies = policies;

        builder = new BlobClientBuilder();
    }

    @Override
    public BlobClientBuilder build() {
        /*// 1. Use the customized http pipeline instance, this is created by the user properties configuration.
        HttpPipelineBuilder pipelineBuilder = new HttpPipelineBuilder();
        HttpPipelinePoliciesSupplier policiesSupplier = getHttpPipelinePolicySupplier();
        HttpClientSupplier clientSupplier = getHttpClientSupplier();
        if (policiesSupplier != null && clientSupplier != null) {
            List<HttpPipelinePolicy> pipelinePolicies = policiesSupplier.get();
            if (!CollectionUtils.isEmpty(pipelinePolicies)) {
                pipeline = pipelineBuilder.httpClient(clientSupplier.get())
                                          .policies(pipelinePolicies.toArray(new HttpPipelinePolicy[0]))
                                          .build();
                builder.pipeline(pipeline);
                return builder;
            }
        }

        // 2. Apply the customized http pipeline policies, which are provided by developers
        Optional.ofNullable(policies).ifPresent(list -> list.forEach(this::addPolicy));

        // 3. Apply the available common configuration for builder
        customerProvidedKey(blobProperties.getCustomerProvidedKey());
        Optional.ofNullable(blobProperties.getCustomerProvidedKey()).ifPresent(this::customerProvidedKey);
        Optional.ofNullable(blobProperties.getEncryptionScope()).ifPresent(this::encryptionScope);

        if (StringUtils.hasText(blobProperties.getAccountName())
            && StringUtils.hasText(blobProperties.getAccountKey())) {
            credential(new StorageSharedKeyCredential(blobProperties.getAccountName(), blobProperties.getAccountKey()));
        }
        TokenCredential tokenCredential = getTokenCredential(blobProperties);

        credential(tokenCredential);
        if (StringUtils.hasText(blobProperties.getSignature())) {
            credential(new AzureSasCredential(blobProperties.getSignature()));
        }
        if (StringUtils.hasText(blobProperties.getSasToken())) {
            sasToken(blobProperties.getSasToken());
        }
        if (blobProperties.getAnonymousAccess()) {
            setAnonymousAccess();
        }

        Optional.ofNullable(blobProperties.getConnectionString()).ifPresent(this::connectionString);
        Optional.ofNullable(blobProperties.getEndpoint()).ifPresent(this::endpoint);
        Optional.ofNullable(blobProperties.getBlobName()).ifPresent(this::blobName);
        Optional.ofNullable(blobProperties.getContainerName()).ifPresent(this::containerName);
        Optional.ofNullable(blobProperties.getSnapshot()).ifPresent(this::snapshot);
        Optional.ofNullable(blobProperties.getVersionId()).ifPresent(this::versionId);
        Optional.ofNullable(blobProperties.getServiceVersion()).ifPresent(this::serviceVersion);

        httpClientOptions(getHttpClientOptions(blobProperties.getHttp().getClient()));
        configuration(getDefaultConfiguration());*/
        return builder;
    }

    @Override
    public void setProxy(ProxyProperties proxy) {

    }

    @Override
    public void setTokenCredential(TokenCredential credential) {

    }
}
