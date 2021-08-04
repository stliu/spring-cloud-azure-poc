package com.azure.spring.autoconfigure.keyvault;

import com.azure.spring.core.ServiceEndpointProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault")
public class KeyVaultProperties implements ServiceEndpointProvider {
    private String endpoint;

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
