package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.security.keyvault.secrets.SecretServiceVersion;
import com.azure.spring.autoconfigure.core.ServiceVersionProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault.secret")
public class KeyVaultSecretProperties implements ServiceVersionProvider<SecretServiceVersion> {

    private SecretServiceVersion serviceVersion;

    @Override
    public SecretServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(SecretServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }
}
