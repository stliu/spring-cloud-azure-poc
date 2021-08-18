package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.builder.AzureHttpClientBuilderFactory;
import com.azure.spring.core.context.AzureSpringHttpConfigurationContext;
import com.azure.spring.core.properties.http.HttpProperties;


public class KeyVaultCertificateServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<CertificateClientBuilder> {

    private CertificateClientBuilder builder;
    private final KeyVaultProperties keyVaultProperties;
    private final KeyVaultCertificateProperties keyVaultCertificateProperties;
    private final HttpProperties httpProperties;
    private final TokenCredential defaultTokenCredential;

    private HttpPipeline pipeline;

    private AzureSpringHttpConfigurationContext httpContext;

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
    public CertificateClientBuilder build() {
        builder = new CertificateClientBuilder();

//        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
//        map.from(keyVaultProperties.getEndpoint()).to(this::vaultUrl);
//        map.from(keyVaultCertificateProperties.getServiceVersion()).to(this::serviceVersion);
//
//        // Customize the http pipeline
//        Optional.ofNullable(httpProperties)
//                .ifPresent(p -> {
//                    HttpPipeline hp = new HttpPipelineBuilder()
//                        .httpClient(getHttpClientSupplier().get())
//                        .policies(getHttpPipelinePolicySupplier().get().toArray(new HttpPipelinePolicy[0]))
//                        .build();
//                    builder.pipeline(hp);
//                });
//
//        // apply the credential
//        supportFeatures().forEach(feature -> {
//            if (feature == AzureServiceFeature.TOKEN_CREDENTIAL) {
//                builder.credential(new AzureTokenCredentialResolver().resolve(defaultTokenCredential,
//                    keyVaultCertificateProperties));
//            } else {
//                throw new IllegalStateException("KeyVault certificate starter does not "
//                    + "support the feature type " + feature + ".");
//            }
//        });
        return builder;
    }

    public KeyVaultCertificateServiceClientBuilderFactory vaultUrl(String vaultUrl) {
        builder.vaultUrl(vaultUrl);
        return this;
    }

    public KeyVaultCertificateServiceClientBuilderFactory serviceVersion(CertificateServiceVersion version) {
        builder.serviceVersion(version);
        return this;
    }

}
