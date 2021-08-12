package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.core.credential.TokenCredential;
import com.azure.core.exception.AzureException;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.spring.core.AzureSpringConfigurationContext;
import com.azure.spring.core.builder.AzureAmqpClientBuilderFactory;
import com.azure.spring.core.amqp.AzureSpringAmqpConfigurationContext;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.spring.core.properties.amqp.AmqpProperties;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.Optional;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client
 * according the configuration context and blob properties.
 */
public class AzureEventHubClientBuilderFactory implements
    AzureAmqpClientBuilderFactory<EventHubClientBuilder> {

    private final AzureSpringAmqpConfigurationContext configurationContext;
    private final EventHub eventHubProperties;

    private EventHubClientBuilder builder;

    public AzureEventHubClientBuilderFactory(AzureSpringAmqpConfigurationContext configurationContext,
                                             EventHub eventHubProperties) {
        this.configurationContext = configurationContext;
        this.eventHubProperties = eventHubProperties;

        builder = new EventHubClientBuilder();
    }

    @Override
    public EventHubClientBuilder build() {
        /*Optional.ofNullable(eventHubProperties.getConnectionString()).ifPresent(this::connectionString);
        clientOptions(getClientOptions(eventHubProperties.getAmqp().getClient()));
        builder.configuration(configurationContext.getAzureDefaultConfiguration());
        Optional.ofNullable(eventHubProperties.getCustomEndpointAddress()).ifPresent(this::customEndpointAddress);

        if (eventHubProperties.getSharedConnection()) {
            shareConnection();
        }
        if (StringUtils.hasText(eventHubProperties.getFullyQualifiedNamespace())
            && StringUtils.hasText(eventHubProperties.getEventHubName())) {
            credential(eventHubProperties.getFullyQualifiedNamespace(), eventHubProperties.getEventHubName(), getTokenCredential(eventHubProperties));
        }
        if (StringUtils.hasText(eventHubProperties.getConnectionString())
            && StringUtils.hasText(eventHubProperties.getEventHubName())) {
            connectionString(eventHubProperties.getConnectionString(), eventHubProperties.getEventHubName());
        }

        proxyOptions(getProxyOptions(eventHubProperties.getAmqp().getProxy()));
        retry(getAmqpRetryOptions(eventHubProperties.getAmqp().getRetry()));
        Optional.ofNullable(eventHubProperties.getTransport()).ifPresent(this::transportType);
        Optional.ofNullable(eventHubProperties.getConsumerGroup()).ifPresent(this::consumerGroup);
        Optional.ofNullable(eventHubProperties.getPrefetchCount()).ifPresent(this::prefetchCount);*/
        return builder;
    }

    @Override
    public void setProxy(ProxyProperties proxy) {

    }

    @Override
    public void setTokenCredential(TokenCredential credential) {

    }
}
