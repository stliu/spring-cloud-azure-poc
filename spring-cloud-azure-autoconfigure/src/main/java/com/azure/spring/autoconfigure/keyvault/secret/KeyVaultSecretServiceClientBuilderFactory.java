package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.SecretServiceVersion;
import com.azure.spring.core.AzureSpringConfigurationContext;
import com.azure.spring.core.http.HttpClientSupplier;
import com.azure.spring.core.properties.ClientOptionsProperties;
import com.azure.spring.core.properties.http.HttpProperties;
import com.azure.spring.core.builder.AzureHttpClientBuilderFactory;
import com.azure.spring.core.aware.HttpPipelineAware;
import com.azure.spring.core.aware.TokenCredentialAware;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateProperties;
import com.azure.spring.core.http.HttpPipelinePoliciesSupplier;
import org.springframework.boot.context.properties.PropertyMapper;

public class KeyVaultSecretServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<SecretClientBuilder>, TokenCredentialAware, HttpPipelineAware {

    private SecretClientBuilder delegated;
    private KeyVaultProperties keyVaultProperties;
    private KeyVaultCertificateProperties keyVaultCertificateProperties;

    public KeyVaultSecretServiceClientBuilderFactory(KeyVaultProperties keyVaultProperties,
                                                     KeyVaultCertificateProperties keyVaultCertificateProperties) {
        this.keyVaultProperties = keyVaultProperties;
        this.keyVaultCertificateProperties = keyVaultCertificateProperties;

        delegated = new SecretClientBuilder();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(keyVaultProperties.getEndpoint()).to(this::vaultUrl);
    }

    @Override
    public HttpProperties getRootHttpProperties() {
        return null;
    }

    @Override
    public HttpProperties getInheritHttpProperties() {
        return null;
    }

    @Override
    public HttpPipelinePoliciesSupplier getHttpPipelinePolicySupplier() {
        return null;
    }

    @Override
    public HttpClientSupplier getHttpClientSupplier() {
        return null;
    }

    @Override
    public ClientOptions getClientOptions(ClientOptionsProperties client) {
        return null;
    }

    @Override
    public Configuration getDefaultConfiguration() {
        return null;
    }

    @Override
    public TokenCredential getDefaultTokenCredential() {
        return null;
    }

    @Override
    public void setPipeline(HttpPipeline pipeline) {

    }

    @Override
    public void setTokenCredential(TokenCredential credential) {

    }

    @Override
    public AzureSpringConfigurationContext getConfigurationContext() {
        return null;
    }

    @Override
    public SecretClientBuilder build() {
        return delegated;
    }

    @Override
    public SecretClientBuilder builder() {
        return null;
    }

    /*@Override
    public List<AzureServiceFeature> supportFeatures() {
        return null;
    }
*/

    public KeyVaultSecretServiceClientBuilderFactory vaultUrl(String vaultUrl) {
        delegated.vaultUrl(vaultUrl);
        return this;
    }

    public KeyVaultSecretServiceClientBuilderFactory serviceVersion(SecretServiceVersion version) {
        delegated.serviceVersion(version);
        return this;
    }

}
