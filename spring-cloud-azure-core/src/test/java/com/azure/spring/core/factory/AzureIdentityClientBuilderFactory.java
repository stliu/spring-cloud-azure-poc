package com.azure.spring.core.factory;

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.core.util.Configuration;
import com.azure.identity.implementation.IdentityClientBuilder;
import com.azure.identity.implementation.IdentityClientOptions;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.RawTokenAuthenticationDescriptor;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.IdentityClientProperties;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;
import com.azure.spring.core.properties.retry.RetryProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class AzureIdentityClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<IdentityClientBuilder> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AzureIdentityClientBuilderFactory.class);

    private final IdentityClientProperties properties;
    private final IdentityClientOptions identityClientOptions = new IdentityClientOptions();

    public AzureIdentityClientBuilderFactory(IdentityClientProperties properties) {
        this.properties = properties;
    }

    @Override
    protected BiConsumer<IdentityClientBuilder, HttpClient> consumeHttpClient() {
        return (b, c) -> identityClientOptions.setHttpClient(c);
    }

    @Override
    protected BiConsumer<IdentityClientBuilder, HttpPipelinePolicy> consumeHttpPipelinePolicy() {
        return (a, b) -> {
            LOGGER.warn("HttpPipelinePolicy is not supported to configure in the identity client builder.");
        };
    }

    @Override
    protected void configureRetry(IdentityClientBuilder builder) {
        final RetryProperties retryProperties = properties.getRetry();
        if (retryProperties == null) {
            return;
        }
        identityClientOptions.setMaxRetry(retryProperties.getMaxAttempts());
        identityClientOptions.setRetryTimeout(i -> Duration.ofSeconds((long)Math.pow(2.0D, (double)(i.getSeconds() - 1L))));
    }

    @Override
    protected IdentityClientBuilder createBuilderInstance() {
        final IdentityClientBuilder identityClientBuilder = new IdentityClientBuilder();
        identityClientBuilder.identityClientOptions(this.identityClientOptions);
        return identityClientBuilder;
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return this.properties;
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(IdentityClientBuilder builder) {
        return Collections.singletonList(new RawTokenAuthenticationDescriptor(provider -> {
            final TokenCredentialProperties credential = provider.getCredential();
            builder.tenantId(credential.getTenantId());
            builder.clientId(credential.getClientId());
            builder.clientSecret(credential.getClientSecret());
            builder.certificatePath(credential.getCertificatePath());
            builder.certificatePassword(credential.getCertificatePassword());
        }));
    }

    @Override
    protected void configureService(IdentityClientBuilder builder) {
        builder.sharedTokenCacheCredential(properties.isSharedTokenCacheCred());
    }

    @Override
    protected BiConsumer<IdentityClientBuilder, Configuration> consumeConfiguration() {
        return (b, c) -> {};
    }


}