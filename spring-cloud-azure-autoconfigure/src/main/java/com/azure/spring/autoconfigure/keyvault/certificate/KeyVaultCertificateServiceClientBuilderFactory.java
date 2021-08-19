package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.http.HttpClient;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.factory.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;

import java.util.List;


public class KeyVaultCertificateServiceClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<CertificateClientBuilder> {

    private final KeyVaultProperties keyVaultProperties;


    public KeyVaultCertificateServiceClientBuilderFactory(KeyVaultProperties keyVaultProperties) {
        this.keyVaultProperties = keyVaultProperties;
    }


    @Override
    protected void configureHttpClient(CertificateClientBuilder builder, HttpClient httpClient) {
        builder.httpClient(httpClient);
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
}
