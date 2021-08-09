package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.HttpPipelinePosition;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.spring.core.AzureProperties;
import com.azure.spring.core.AzureTokenCredentialResolver;
import com.azure.spring.core.http.AzureHttpClientBuilderFactory;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import com.azure.spring.core.http.HttpClientSupplier;
import com.azure.spring.core.http.HttpPipelinePoliciesSupplier;
import com.azure.spring.core.identify.AzureServiceFeature;
import com.azure.spring.core.properties.HttpProperties;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.storage.blob.BlobClientBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

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
    private HttpProperties http;
    private ProxyProperties  proxy;
    private AzureProperties rootAzureProperties;
    private TokenCredential defaultTokenCredential;
    private HttpPipeline pipeline;


    public AzureStorageBlobServiceClientBuilderFactory(AzureSpringHttpConfigurationContext configurationContext,
                                                       AzureStorageBlobProperties blobProperties,
                                                       List<HttpPipelinePolicy> policies) {
        this.configurationContext = configurationContext;
        this.blobProperties = blobProperties;
        this.policies = policies;

        this.http = configurationContext.getHttpProperties();
        this.proxy = configurationContext.getProxyProperties();
        this.rootAzureProperties = configurationContext.getRootAzureProperties();
        this.defaultTokenCredential = configurationContext.getTokenCredential();
    }

    @Override
    public HttpProperties getRootHttpProperties() {
        return configurationContext.getHttpProperties();
    }

    @Override
    public HttpProperties getInheritHttpProperties() {
        return blobProperties.getHttp();
    }

    @Override
    public BlobClientBuilder build() {
        builder = new BlobClientBuilder();

        // 1. Use the customized http pipeline instance, this is created by the user properties configuration.
        HttpPipelineBuilder pipelineBuilder = new HttpPipelineBuilder();
        List<HttpPipelinePolicy> pipelinePolicies = getHttpPipelinePolicySupplier().get();
        if (pipelinePolicies != null) {
            pipeline = pipelineBuilder.httpClient(getHttpClientSupplier().get())
                                      .policies(pipelinePolicies.toArray(new HttpPipelinePolicy[0]))
                                      .build();
            builder.pipeline(pipeline);
            return builder;
        }

        // 2. Apply the customized http pipeline policies, which are provided by developers
        Optional.ofNullable(policies).ifPresent(list -> list.forEach(builder::addPolicy));
        // TODO: it's necessary to check the validation of user provided pipeline policy, right?
        return builder;
    }

    // TODO: will use post processor to set the pipeline?
    @Override
    public void setPipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public List<AzureServiceFeature> supportFeatures() {
        return Collections.unmodifiableList(Arrays.asList(
            AzureServiceFeature.TOKEN_CREDENTIAL,
            AzureServiceFeature.SAS_TOKEN_CREDENTIAL,
            AzureServiceFeature.CONNECTION_STRING));
    }
}
