package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.http.HttpClient;
import com.azure.core.util.Configuration;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.factory.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;

import java.util.List;
import java.util.function.BiConsumer;


public class KeyVaultCertificateServiceClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<CertificateClientBuilder> {

    private final KeyVaultProperties keyVaultProperties;


    public KeyVaultCertificateServiceClientBuilderFactory(KeyVaultProperties keyVaultProperties) {
        this.keyVaultProperties = keyVaultProperties;
    }

    @Override
    protected BiConsumer<CertificateClientBuilder, HttpClient> consumeHttpClient() {
        return CertificateClientBuilder::httpClient;
    }

    @Override
    protected CertificateClientBuilder createBuilderInstance() {
        return new CertificateClientBuilder();
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return this.keyVaultProperties;
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(CertificateClientBuilder builder) {
        return null;
    }

    @Override
    protected void configureService(CertificateClientBuilder builder) {

    }

    @Override
    protected BiConsumer<CertificateClientBuilder, Configuration> consumeConfiguration() {
        return CertificateClientBuilder::configuration;
    }
}
