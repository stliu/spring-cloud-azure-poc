package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.spring.autoconfigure.core.ServiceClientBuilderCustomizer;

public interface KeyVaultCertificateBuilderCustomizer extends ServiceClientBuilderCustomizer<CertificateClientBuilder> {

    @Override
    default void customize(CertificateClientBuilder builder) {

    }
}
