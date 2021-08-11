package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.security.keyvault.certificates.CertificateServiceVersion;
import com.azure.spring.autoconfigure.core.ServiceVersionProvider;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.HttpPropertiesAware;
import com.azure.spring.core.properties.http.HttpProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault.certificate")
public class KeyVaultCertificateProperties extends AzureProperties implements
    HttpPropertiesAware, ServiceVersionProvider<CertificateServiceVersion> {

    private CertificateServiceVersion serviceVersion;

    private HttpProperties http;

    public HttpProperties getHttp() {
        return http;
    }

    @Override
    public void setHttp(HttpProperties http) {
        this.http = http;
    }

    @Override
    public CertificateServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(CertificateServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }
}
