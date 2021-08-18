package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.SecretServiceVersion;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateProperties;
import com.azure.spring.core.builder.AzureHttpClientBuilderFactory;
import org.springframework.boot.context.properties.PropertyMapper;

public class KeyVaultSecretServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<SecretClientBuilder> {

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

    public KeyVaultSecretServiceClientBuilderFactory vaultUrl(String vaultUrl) {
        delegated.vaultUrl(vaultUrl);
        return this;
    }

    public KeyVaultSecretServiceClientBuilderFactory serviceVersion(SecretServiceVersion version) {
        delegated.serviceVersion(version);
        return this;
    }

}
