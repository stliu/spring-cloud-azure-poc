package com.azure.spring.autoconfigure.cosmos;

import com.azure.core.util.Header;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.KeyAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.TokenAuthenticationDescriptor;
import com.azure.spring.core.factory.AbstractAzureServiceClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;

import java.util.Arrays;
import java.util.List;


public class CosmosServiceClientBuilderFactory extends AbstractAzureServiceClientBuilderFactory<CosmosClientBuilder> {

    private final CosmosProperties cosmosProperties;

    public CosmosServiceClientBuilderFactory(CosmosProperties cosmosProperties) {
        this.cosmosProperties = cosmosProperties;
    }

    @Override
    protected CosmosClientBuilder createBuilderInstance() {
        return new CosmosClientBuilder();
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return this.cosmosProperties;
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(CosmosClientBuilder builder) {
        return Arrays.asList(
            new KeyAuthenticationDescriptor(provider -> builder.credential(provider.getCredential())),
            new TokenAuthenticationDescriptor(provider -> builder.credential(provider.getCredential()))
        );
    }

    @Override
    protected void configureApplicationId(CosmosClientBuilder builder, String applicationId) {
        builder.userAgentSuffix(applicationId);
    }

    @Override
    protected void configureHeaders(CosmosClientBuilder builder, List<Header> headers) {
        // empty
    }

    @Override
    protected void configureClient(CosmosClientBuilder builder) {

    }

    @Override
    protected void configureProxy(CosmosClientBuilder builder) {

    }

    @Override
    protected void configureRetry(CosmosClientBuilder builder) {

    }

    @Override
    protected void configureService(CosmosClientBuilder builder) {

    }
}
