package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.SecretServiceVersion;
import com.azure.spring.autoconfigure.core.AzureServiceClientBuilder;
import com.azure.spring.autoconfigure.core.SpringConnectionString;
import com.azure.spring.autoconfigure.core.SpringKeyCredential;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateProperties;
import org.springframework.boot.context.properties.PropertyMapper;

public class KeyVaultSecretServiceClientBuilder implements AzureServiceClientBuilder<SecretClientBuilder> {

    private SecretClientBuilder delegated;
    private KeyVaultProperties keyVaultProperties;
    private KeyVaultCertificateProperties keyVaultCertificateProperties;

    public KeyVaultSecretServiceClientBuilder(KeyVaultProperties keyVaultProperties,
                                              KeyVaultCertificateProperties keyVaultCertificateProperties) {
        this.keyVaultProperties = keyVaultProperties;
        this.keyVaultCertificateProperties = keyVaultCertificateProperties;

        delegated = new SecretClientBuilder();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(keyVaultProperties.getEndpoint()).to(this::vaultUrl);
//        map.from(keyVaultCertificateProperties.getServiceVersion()).to(this::serviceVersion);
    }

    @Override
    public void httpLogOptions(HttpLogOptions logOptions) {

    }

    @Override
    public void pipeline(HttpPipeline pipeline) {

    }

    @Override
    public void springConnectionString(SpringConnectionString springConnectionString) {

    }

    @Override
    public void springKeyCredential(SpringKeyCredential springKeyCredential) {

    }

    @Override
    public void tokenCredential(TokenCredential credential) {

    }

    @Override
    public SecretClientBuilder build() {
        return delegated;
    }

    public KeyVaultSecretServiceClientBuilder vaultUrl(String vaultUrl) {
        delegated.vaultUrl(vaultUrl);
        return this;
    }

    public KeyVaultSecretServiceClientBuilder serviceVersion(SecretServiceVersion version) {
        delegated.serviceVersion(version);
        return this;
    }

}
