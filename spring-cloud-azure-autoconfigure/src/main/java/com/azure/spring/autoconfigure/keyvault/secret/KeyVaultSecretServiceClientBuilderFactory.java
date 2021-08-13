package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.SecretServiceVersion;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.spring.core.builder.AzureHttpClientBuilderFactory;
import com.azure.spring.core.aware.HttpPipelineAware;
import com.azure.spring.core.aware.credential.TokenCredentialAware;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateProperties;
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
    public SecretClientBuilder build() {
        return null;
    }

    @Override
    public void setPipeline(HttpPipeline pipeline) {

    }

    @Override
    public void setProxy(ProxyProperties proxy) {

    }

    @Override
    public void setTokenCredential(TokenCredential credential) {

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
