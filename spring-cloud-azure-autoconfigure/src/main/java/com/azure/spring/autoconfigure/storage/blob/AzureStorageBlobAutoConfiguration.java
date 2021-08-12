package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import com.azure.storage.blob.BlobAsyncClient;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(BlobClientBuilder.class)
@ConditionalOnProperty(prefix = "spring.cloud.azure.storage.blob", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(AzureStorageBlob.class)
@AutoConfigureAfter
public class AzureStorageBlobAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BlobClientBuilder blobClientBuilder() {
        BlobClientBuilder builder = new BlobClientBuilder();

        return builder;
    }

    @Bean
    @ConditionalOnMissingBean
    public BlobClient blobClient(BlobClientBuilder builder) {
        return builder.buildClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public BlobAsyncClient blobAsyncClient(BlobClientBuilder builder) {
        return builder.buildAsyncClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public AzureBlobClientBuilderFactory factory(AzureSpringHttpConfigurationContext context,
                                                 AzureStorageBlob properties,
                                                 ObjectProvider<HttpPipelinePolicy> policies) {
        return new AzureBlobClientBuilderFactory(context, properties, policies.stream().collect(Collectors.toList()));
    }

    @Bean
    @ConditionalOnMissingBean
    public BlobClientBuilder blobClientBuilder(AzureBlobClientBuilderFactory factory) {
        return factory.build();
    }
}
