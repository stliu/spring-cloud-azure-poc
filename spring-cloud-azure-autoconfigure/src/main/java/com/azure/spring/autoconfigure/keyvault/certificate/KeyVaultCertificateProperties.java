package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.spring.autoconfigure.core.ServiceVersionProvider;
import com.azure.spring.core.AzureProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault.certificate")
public class KeyVaultCertificateProperties extends AzureProperties implements ServiceVersionProvider<CertificateServiceVersion> {

    private CertificateServiceVersion serviceVersion;

    @Override
    public CertificateServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(CertificateServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }
}
