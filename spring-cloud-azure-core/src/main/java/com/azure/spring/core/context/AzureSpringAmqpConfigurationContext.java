package com.azure.spring.core.context;

import com.azure.spring.core.context.AzureSpringConfigurationContext;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.amqp.AmqpProperties;

/**
 * Common amqp configuration context of module-based amqp protocol.
 */
public interface AzureSpringAmqpConfigurationContext extends AzureSpringConfigurationContext {

    AzureProperties getRootAzureProperties();
    AmqpProperties getRootAmqpProperties();
}
