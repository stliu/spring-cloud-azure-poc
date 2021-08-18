package com.azure.spring.service.factory;

import com.azure.core.util.HttpClientOptions;
import com.azure.identity.implementation.IdentityClientBuilder;
import com.azure.identity.implementation.IdentityClientOptions;
import com.azure.spring.core.builder.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.properties.AzureProperties;

import java.util.List;

public class AzureIdentityClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<IdentityClientBuilder, IdentityClientOptions, Object> {

    private final IdentifyClientProperties properties;

    public AzureIdentityClientBuilderFactory(IdentifyClientProperties properties) {
        this.properties = properties;
    }

    @Override
    protected IdentityClientBuilder createBuilderInstance() {
        return new IdentityClientBuilder();
    }

    @Override
    protected void configureService(IdentityClientBuilder builder) {

    }

    @Override
    protected AzureProperties getAzureProperties() {
        return null;
    }

    @Override
    protected List<AuthenticationDescriptor> getAuthenticationDescriptors(IdentityClientBuilder builder) {
        return null;
    }
}
