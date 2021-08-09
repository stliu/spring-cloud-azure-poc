package com.azure.spring.core.http;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.AzureProperties;
import com.azure.spring.core.properties.HttpProperties;
import com.azure.spring.core.properties.ProxyProperties;
import org.springframework.core.env.Environment;

/**
 * Default implementation for http configuration context,suitable for module-based http protocol.
 */
public class DefaultAzureSpringHttpConfigurationContext implements AzureSpringHttpConfigurationContext {

    private final AzureProperties azureProperties;
    private final Environment environment;

    public DefaultAzureSpringHttpConfigurationContext(AzureProperties azureProperties,
                                                      Environment environment) {
        this.azureProperties = azureProperties;
        this.environment = environment;
    }

    @Override
    public HttpProperties getHttpProperties() {

        return azureProperties.getHttp();
    }

    @Override
    public ProxyProperties getProxyProperties() {
        return azureProperties.getProxy();
    }

    @Override
    public AzureProperties getRootAzureProperties() {
        return azureProperties;
    }

    @Override
    public TokenCredential getTokenCredential() {
        // TODO: Create the final chained token credential.
        return null;
    }
}
