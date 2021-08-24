package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.core.http.HttpClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.TokenAuthenticationDescriptor;
import com.azure.spring.core.factory.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KeyVaultSecretServiceClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<SecretClientBuilder> {

    private final KeyVaultProperties keyVaultProperties;

    public KeyVaultSecretServiceClientBuilderFactory(KeyVaultProperties keyVaultProperties) {
        this.keyVaultProperties = keyVaultProperties;
    }

    @Override
    protected SecretClientBuilder createBuilderInstance() {
        return new SecretClientBuilder();
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return this.keyVaultProperties;
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(SecretClientBuilder builder) {
        return Collections.singletonList(
            new TokenAuthenticationDescriptor(provider -> builder.credential(provider.getCredential())));
    }

    @Override
    protected void configureService(SecretClientBuilder builder) {

    }

    @Override
    protected void configureHttpClient(SecretClientBuilder builder, HttpClient httpClient) {
        builder.httpClient(httpClient);
    }
}
