package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.AzureTokenCredentialResolver;
import com.azure.spring.core.http.AzureHttpClientBuilderFactory;
import com.azure.spring.core.http.HttpProperties;
import com.azure.spring.core.identify.AzureServiceFeature;
import org.springframework.boot.context.properties.PropertyMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class KeyVaultCertificateServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<CertificateClientBuilder> {

    private CertificateClientBuilder delegated;
    private KeyVaultProperties keyVaultProperties;
    private KeyVaultCertificateProperties keyVaultCertificateProperties;
    private HttpProperties httpProperties;
    private TokenCredential defaultTokenCredential;

    public KeyVaultCertificateServiceClientBuilderFactory(TokenCredential defaultTokenCredential,
                                                          KeyVaultProperties keyVaultProperties,
                                                          KeyVaultCertificateProperties keyVaultCertificateProperties,
                                                          HttpProperties httpProperties) {
        this.defaultTokenCredential = defaultTokenCredential;
        this.keyVaultProperties = keyVaultProperties;
        this.keyVaultCertificateProperties = keyVaultCertificateProperties;
        this.httpProperties = httpProperties;
    }

    @Override
    public HttpProperties getHttpProperties() {
        return httpProperties;
    }

    @Override
    public CertificateClientBuilder build() {
        delegated = new CertificateClientBuilder();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(keyVaultProperties.getEndpoint()).to(this::vaultUrl);
        map.from(keyVaultCertificateProperties.getServiceVersion()).to(this::serviceVersion);

        // Customize the http pipeline
        Optional.ofNullable(httpProperties)
                .filter(this::enabledCustomizer)
                .ifPresent(p -> {
                    HttpPipeline hp = new HttpPipelineBuilder()
                        .httpClient(getHttpClientSupplier().get())
                        .policies(getHttpPipelinePolicySupplier().get().toArray(new HttpPipelinePolicy[0]))
                        .build();
                    delegated.pipeline(hp);
                });

        // apply the credential
        supportFeatures().forEach(feature -> {
            switch (feature) {
                case TOKEN_CREDENTIAL:
                    delegated.credential(new AzureTokenCredentialResolver().resolve(defaultTokenCredential, keyVaultCertificateProperties));
                    break;
                default:
                    throw new IllegalStateException("KeyVault certificate starter does not "
                        + "support the feature type" + feature + ".");
            }
        });
        return delegated;
    }

    public boolean enabledCustomizer(HttpProperties httpProperties) {
        return httpProperties.getEnabledCustomizer();
    }

    @Override
    public List<AzureServiceFeature> supportFeatures() {
        return Arrays.asList(AzureServiceFeature.TOKEN_CREDENTIAL);
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
