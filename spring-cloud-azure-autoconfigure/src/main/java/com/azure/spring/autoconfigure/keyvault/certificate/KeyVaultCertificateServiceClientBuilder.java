package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.spring.autoconfigure.core.AzureServiceClientBuilder;
import com.azure.spring.autoconfigure.core.SpringConnectionString;
import com.azure.spring.autoconfigure.core.SpringKeyCredential;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import org.springframework.boot.context.properties.PropertyMapper;

public class KeyVaultCertificateServiceClientBuilder implements AzureServiceClientBuilder<CertificateClientBuilder> {

    private CertificateClientBuilder delegated;
    private KeyVaultProperties keyVaultProperties;
    private KeyVaultCertificateProperties keyVaultCertificateProperties;

    public KeyVaultCertificateServiceClientBuilder(KeyVaultProperties keyVaultProperties,
                                                   KeyVaultCertificateProperties keyVaultCertificateProperties) {
        this.keyVaultProperties = keyVaultProperties;
        this.keyVaultCertificateProperties = keyVaultCertificateProperties;

        delegated = new CertificateClientBuilder();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(keyVaultProperties.getEndpoint()).to(this::vaultUrl);
        map.from(keyVaultCertificateProperties.getServiceVersion()).to(this::serviceVersion);
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
    public CertificateClientBuilder build() {
        return delegated;
    }

    public KeyVaultCertificateServiceClientBuilder vaultUrl(String vaultUrl) {
        delegated.vaultUrl(vaultUrl);
        return this;
    }

    public KeyVaultCertificateServiceClientBuilder serviceVersion(CertificateServiceVersion version) {
        delegated.serviceVersion(version);
        return this;
    }

}
