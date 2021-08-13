package com.azure.spring.core.context;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.amqp.AmqpProperties;
import org.springframework.core.env.Environment;

/**
 * Default implementation for amqp configuration context,suitable for module-based amqp protocol.
 */
public class DefaultAzureSpringAmqpConfigurationContext implements AzureSpringAmqpConfigurationContext {

    private final TokenCredential tokenCredential;
    private final AzureProperties azureProperties;
    private final Environment environment;

    public DefaultAzureSpringAmqpConfigurationContext(TokenCredential tokenCredential,
                                                      AzureProperties azureProperties,
                                                      Environment environment) {
        this.tokenCredential = tokenCredential;
        this.azureProperties = azureProperties;
        this.environment = environment;
    }

    @Override
    public AzureProperties getRootAzureProperties() {
        return azureProperties;
    }

    @Override
    public AmqpProperties getRootAmqpProperties() {
        return null;
    }

    @Override
    public TokenCredential getTokenCredential() {
        // TODO: Create the final chained token credential.
        return null;
    }
}
