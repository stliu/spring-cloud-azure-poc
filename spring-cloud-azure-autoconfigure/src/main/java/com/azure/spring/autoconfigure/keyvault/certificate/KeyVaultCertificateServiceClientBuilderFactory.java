package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import com.azure.spring.core.AzureTokenCredentialResolver;
import com.azure.spring.core.http.AzureHttpClientBuilderFactory;
import com.azure.spring.core.properties.HttpProperties;
import com.azure.spring.core.identify.AzureServiceFeature;
import org.springframework.boot.context.properties.PropertyMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


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
    public HttpProperties getRootHttpProperties() {
        return null;
    }

    @Override
    public HttpProperties getInheritHttpProperties() {
        return null;
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

    @Override
    public void setPipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public List<AzureServiceFeature> supportFeatures() {
        return Collections.singletonList(AzureServiceFeature.TOKEN_CREDENTIAL);
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
