package com.azure.spring.core.factory;

import com.azure.core.http.HttpClient;
import com.azure.identity.implementation.IdentityClientBuilder;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.IdentifyClientProperties;

import java.util.List;

public class AzureIdentityClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<IdentityClientBuilder> {

    private final IdentifyClientProperties properties;

    public AzureIdentityClientBuilderFactory(IdentifyClientProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void configureHttpClient(IdentityClientBuilder builder, HttpClient httpClient) {

    }

    @Override
    protected void configureClient(IdentityClientBuilder builder) {

    }

    @Override
    protected IdentityClientBuilder createBuilderInstance() {
        return new IdentityClientBuilder();
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return this.properties;
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(IdentityClientBuilder builder) {
        return null;
    }

    @Override
    protected void configureService(IdentityClientBuilder builder) {

    }


}
