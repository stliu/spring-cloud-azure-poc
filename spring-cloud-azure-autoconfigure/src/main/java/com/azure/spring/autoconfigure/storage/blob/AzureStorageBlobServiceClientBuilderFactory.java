package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.credential.AzureSasCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.spring.core.AzureSpringConfigurationContext;
import com.azure.spring.core.http.AzureHttpClientBuilderFactory;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import com.azure.spring.core.http.HttpClientSupplier;
import com.azure.spring.core.http.HttpPipelinePoliciesSupplier;
import com.azure.spring.core.properties.http.HttpProperties;
import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.BlobServiceVersion;
import com.azure.storage.blob.models.CustomerProvidedKey;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client
 * according the configuration context and blob properties.
 */
public class AzureStorageBlobServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<BlobClientBuilder> {

    private final AzureSpringHttpConfigurationContext configurationContext;
    private final AzureStorageBlobProperties blobProperties;
    private final List<HttpPipelinePolicy> policies;

    private BlobClientBuilder builder;
    private HttpPipeline pipeline;

    public AzureStorageBlobServiceClientBuilderFactory(AzureSpringHttpConfigurationContext configurationContext,
                                                       AzureStorageBlobProperties blobProperties,
                                                       List<HttpPipelinePolicy> policies) {
        this.configurationContext = configurationContext;
        this.blobProperties = blobProperties;
        this.policies = policies;

        builder = new BlobClientBuilder();
    }

    @Override
    public HttpProperties getRootHttpProperties() {
        return configurationContext.getRootHttpProperties();
    }

    @Override
    public HttpProperties getInheritHttpProperties() {
        return blobProperties.getHttp();
    }

    @Override
    public AzureSpringConfigurationContext getConfigurationContext() {
        return configurationContext;
    }

    @Override
    public BlobClientBuilder build() {
        // 1. Use the customized http pipeline instance, this is created by the user properties configuration.
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
        credential(getTokenCredential(blobProperties));
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
        configuration(getDefaultConfiguration());
        return builder;
    }

    @Override
    public BlobClientBuilder builder() {
        return builder;
    }

    // TODO: will use post processor to set the pipeline?
    @Override
    public void setPipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
    }

    /*@Override
    public List<AzureServiceFeature> supportFeatures() {
        return Collections.unmodifiableList(Arrays.asList(
            AzureServiceFeature.TOKEN_CREDENTIAL,
            AzureServiceFeature.SAS_TOKEN_CREDENTIAL,
            AzureServiceFeature.CONNECTION_STRING));
    }*/

    public AzureStorageBlobServiceClientBuilderFactory customerProvidedKey(CustomerProvidedKey customerProvidedKey) {
        builder.customerProvidedKey(customerProvidedKey);
        return this;
    }

    /**
     * Sets the {@code encryption scope} that is used to encrypt blob contents on the server.
     *
     * @param encryptionScope Encryption scope containing the encryption key information.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     */
    public AzureStorageBlobServiceClientBuilderFactory encryptionScope(String encryptionScope) {
        builder.encryptionScope(encryptionScope);
        return this;
    }

    /**
     * Sets the {@link StorageSharedKeyCredential} used to authorize requests sent to the service.
     *
     * @param credential {@link StorageSharedKeyCredential}.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory
     * @throws NullPointerException If {@code credential} is {@code null}.
     */
    public AzureStorageBlobServiceClientBuilderFactory credential(StorageSharedKeyCredential credential) {
        builder.credential(credential);
        return this;
    }

    /**
     * Sets the SAS token used to authorize requests sent to the service.
     *
     * @param sasToken The SAS token to use for authenticating requests.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory
     * @throws NullPointerException If {@code sasToken} is {@code null}.
     */
    public AzureStorageBlobServiceClientBuilderFactory sasToken(String sasToken) {
        builder.sasToken(sasToken);
        return this;
    }

    /**
     * Sets the {@link AzureSasCredential} used to authorize requests sent to the service.
     *
     * @param credential {@link AzureSasCredential} used to authorize requests sent to the service.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory
     * @throws NullPointerException If {@code credential} is {@code null}.
     */
    public AzureStorageBlobServiceClientBuilderFactory credential(AzureSasCredential credential) {
        builder.credential(credential);
        return this;
    }

    /**
     * Clears the credential used to authorize the request.
     *
     * <p>This is for blobs that are publicly accessible.</p>
     *
     * @return the updated AzureStorageBlobServiceClientBuilderFactory
     */
    public AzureStorageBlobServiceClientBuilderFactory setAnonymousAccess() {
        builder.setAnonymousAccess();
        return this;
    }

    /**
     * Sets the connection string to connect to the service.
     *
     * @param connectionString Connection string of the storage account.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory
     * @throws IllegalArgumentException If {@code connectionString} in invalid.
     */
    public AzureStorageBlobServiceClientBuilderFactory connectionString(String connectionString) {
        builder.connectionString(connectionString);
        return this;
    }

    /**
     * Sets the service endpoint, additionally parses it for information (SAS token, container name, blob name)
     *
     * <p>If the blob name contains special characters, pass in the url encoded version of the blob name. </p>
     *
     * <p>If the endpoint is to a blob in the root container, this method will fail as it will interpret the blob name
     * as the container name. With only one path element, it is impossible to distinguish between a container name and
     * a blob in the root container, so it is assumed to be the container name as this is much more common. When working
     * with blobs in the root container, it is best to set the endpoint to the account url and specify the blob name
     * separately using the {@link AzureStorageBlobServiceClientBuilderFactory#blobName(String) blobName} method.</p>
     *
     * @param endpoint URL of the service
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     * @throws IllegalArgumentException If {@code endpoint} is {@code null} or is a malformed URL.
     */
    public AzureStorageBlobServiceClientBuilderFactory endpoint(String endpoint) {
        builder.endpoint(endpoint);
        return this;
    }

    /**
     * Sets the name of the container that contains the blob.
     *
     * @param containerName Name of the container. If the value {@code null} or empty the root container, {@code $root}
     * , will be used.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     */
    public AzureStorageBlobServiceClientBuilderFactory containerName(String containerName) {
        builder.containerName(containerName);
        return this;
    }

    /**
     * Sets the name of the blob.
     *
     * @param blobName Name of the blob. If the blob name contains special characters, pass in the url encoded version
     * of the blob name.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     * @throws NullPointerException If {@code blobName} is {@code null}
     */
    public AzureStorageBlobServiceClientBuilderFactory blobName(String blobName) {
        builder.blobName(blobName);
        return this;
    }

    /**
     * Sets the snapshot identifier of the blob.
     *
     * @param snapshot Snapshot identifier for the blob.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     */
    public AzureStorageBlobServiceClientBuilderFactory snapshot(String snapshot) {
        Optional.ofNullable(snapshot).ifPresent(builder::snapshot);
        return this;
    }

    /**
     * Sets the version identifier of the blob.
     *
     * @param versionId Version identifier for the blob, pass {@code null} to interact with the latest blob version.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     */
    public AzureStorageBlobServiceClientBuilderFactory versionId(String versionId) {
        builder.versionId(versionId);
        return this;
    }

    /**
     * Sets the {@link HttpClient} to use for sending a receiving requests to and from the service.
     *
     * @param httpClient HttpClient to use for requests.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     */
    public AzureStorageBlobServiceClientBuilderFactory httpClient(HttpClient httpClient) {
        builder.httpClient(httpClient);
        return this;
    }

    /**
     * Adds a pipeline policy to apply on each request sent. The policy will be added after the retry policy. If
     * the method is called multiple times, all policies will be added and their order preserved.
     *
     * @param pipelinePolicy a pipeline policy
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     * @throws NullPointerException If {@code pipelinePolicy} is {@code null}.
     */
    public AzureStorageBlobServiceClientBuilderFactory addPolicy(HttpPipelinePolicy pipelinePolicy) {
        builder.addPolicy(pipelinePolicy);
        return this;
    }

    /**
     * Sets the {@link HttpPipeline} to use for the service client.
     *
     * If {@code pipeline} is set, all other settings are ignored, aside from {@link #endpoint(String) endpoint}.
     *
     * @param httpPipeline HttpPipeline to use for sending service requests and receiving responses.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     */
    public AzureStorageBlobServiceClientBuilderFactory pipeline(HttpPipeline httpPipeline) {
        builder.pipeline(pipeline);
        return this;
    }

    /**
     * Sets the {@link BlobServiceVersion} that is used when making API requests.
     * <p>
     * If a service version is not provided, the service version that will be used will be the latest known service
     * version based on the version of the client library being used. If no service version is specified, updating to a
     * newer version of the client library will have the result of potentially moving to a newer service version.
     * <p>
     * Targeting a specific service version may also mean that the service will return an error for newer APIs.
     *
     * @param version {@link BlobServiceVersion} of the service to be used when making requests.
     * @return the updated AzureStorageBlobServiceClientBuilderFactory object
     */
    public AzureStorageBlobServiceClientBuilderFactory serviceVersion(BlobServiceVersion version) {
        builder.serviceVersion(version);
        return this;
    }
}
