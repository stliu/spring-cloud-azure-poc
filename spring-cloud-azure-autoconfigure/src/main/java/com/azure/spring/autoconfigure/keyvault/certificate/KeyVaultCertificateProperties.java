package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.security.keyvault.secrets.SecretServiceVersion;
import com.azure.spring.autoconfigure.core.ServiceVersionProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault.certificate")
public class KeyVaultCertificateProperties implements ServiceVersionProvider<CertificateServiceVersion> {

    private CertificateServiceVersion serviceVersion;

    @Override
    public CertificateServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(CertificateServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }
}
