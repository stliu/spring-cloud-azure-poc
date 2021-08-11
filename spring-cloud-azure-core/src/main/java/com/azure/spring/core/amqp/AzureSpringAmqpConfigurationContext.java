package com.azure.spring.core.amqp;

import com.azure.spring.core.AzureSpringConfigurationContext;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.amqp.AmqpProperties;

/**
 * Common amqp configuration context of module-based amqp protocol.
 */
public interface AzureSpringAmqpConfigurationContext extends AzureSpringConfigurationContext {

    AzureProperties getRootAzureProperties();
    AmqpProperties getRootAmqpProperties();
}
