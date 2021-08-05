package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.AzureServiceFeature;
import com.azure.spring.core.TokenCredentialAware;
import com.azure.spring.core.http.AzureHttpClientBuilderFactory;
import com.azure.spring.core.http.HttpPipelinePoliciesSupplier;
import com.azure.spring.core.http.HttpProperties;
import org.springframework.boot.context.properties.PropertyMapper;

import java.util.List;

public class KeyVaultCertificateServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<CertificateClientBuilder>, TokenCredentialAware {

    private CertificateClientBuilder delegated;
    private KeyVaultProperties keyVaultProperties;
    private KeyVaultCertificateProperties keyVaultCertificateProperties;
    private HttpProperties httpProperties;

    private HttpPipeline pipeline;
    private TokenCredential credential;

    public KeyVaultCertificateServiceClientBuilderFactory(KeyVaultProperties keyVaultProperties,
                                                          KeyVaultCertificateProperties keyVaultCertificateProperties,
                                                          HttpProperties httpProperties) {
        this.keyVaultProperties = keyVaultProperties;
        this.keyVaultCertificateProperties = keyVaultCertificateProperties;
        this.httpProperties = httpProperties;

        delegated = new CertificateClientBuilder();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(keyVaultProperties.getEndpoint()).to(this::vaultUrl);
        map.from(keyVaultCertificateProperties.getServiceVersion()).to(this::serviceVersion);
    }

    @Override
    public HttpPipelinePoliciesSupplier getHttpPipelinePolicySupplier() {
        return null;
    }

    @Override
    public void setPipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public CertificateClientBuilder build() {
        if (pipeline != null) {
            delegated.pipeline(pipeline);
        } else {
            delegated.credential(credential);

        }
        return delegated;
    }

    public List<HttpPipelinePolicy> getHttpPipelinePolicies() {
        return null;
    }

    @Override
    public void setTokenCredential(TokenCredential credential) {
        this.credential = credential;
    }

    @Override
    public List<AzureServiceFeature> supportFeatures() {
        return httpProperties.getFeatures();
    }

    public KeyVaultCertificateServiceClientBuilderFactory vaultUrl(String vaultUrl) {
        delegated.vaultUrl(vaultUrl);
        return this;
    }

    public KeyVaultCertificateServiceClientBuilderFactory serviceVersion(CertificateServiceVersion version) {
        delegated.serviceVersion(version);
        return this;
    }

}
