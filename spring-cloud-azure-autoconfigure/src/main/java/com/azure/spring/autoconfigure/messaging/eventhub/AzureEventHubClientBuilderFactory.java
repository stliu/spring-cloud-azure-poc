package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.core.amqp.AmqpRetryOptions;
import com.azure.core.amqp.AmqpTransportType;
import com.azure.core.amqp.ProxyOptions;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.NamedKeyAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.SasAuthenticationDescriptor;
import com.azure.spring.core.credential.descriptor.TokenAuthenticationDescriptor;
import com.azure.spring.core.factory.AbstractAzureAmqpClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client
 * according the configuration context and blob properties.
 */
public class AzureEventHubClientBuilderFactory extends AbstractAzureAmqpClientBuilderFactory<EventHubClientBuilder> {

    private final EventHubProperties eventHubProperties;

    public AzureEventHubClientBuilderFactory(EventHubProperties eventHubProperties) {
        this.eventHubProperties = eventHubProperties;
    }

    @Override
    protected BiConsumer<EventHubClientBuilder, ProxyOptions> consumeProxyOptions() {
        return EventHubClientBuilder::proxyOptions;
    }

    @Override
    protected BiConsumer<EventHubClientBuilder, AmqpTransportType> consumeAmqpTransportType() {
        return EventHubClientBuilder::transportType;
    }

    @Override
    protected BiConsumer<EventHubClientBuilder, AmqpRetryOptions> consumeAmqpRetryOptions() {
        return EventHubClientBuilder::retry;
    }

    @Override
    protected BiConsumer<EventHubClientBuilder, ClientOptions> consumeClientOptions() {
        return EventHubClientBuilder::clientOptions;
    }

    @Override
    protected BiConsumer<EventHubClientBuilder, Configuration> consumeConfiguration() {
        return EventHubClientBuilder::configuration;
    }

    @Override
    protected EventHubClientBuilder createBuilderInstance() {
        return new EventHubClientBuilder();
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return this.eventHubProperties;
    }

    @Override
    protected void configureService(EventHubClientBuilder builder) {
        builder.consumerGroup(eventHubProperties.getConsumerGroup());
        builder.prefetchCount(eventHubProperties.getPrefetchCount());
        builder.customEndpointAddress(eventHubProperties.getCustomEndpointAddress());
        if (eventHubProperties.isSharedConnection()) {
            builder.shareConnection();
        }
    }

    @Override
    protected List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(EventHubClientBuilder builder) {
        return Arrays.asList(
            new NamedKeyAuthenticationDescriptor(provider -> builder.credential(eventHubProperties.getNamespace(),
                                                                                eventHubProperties.getEventHubName(),
                                                                                provider.getCredential())),
            new SasAuthenticationDescriptor(provider -> builder.credential(eventHubProperties.getNamespace(),
                                                                           eventHubProperties.getEventHubName(),
                                                                           provider.getCredential())),
            new TokenAuthenticationDescriptor(provider -> builder.credential(eventHubProperties.getNamespace(),
                                                                             eventHubProperties.getEventHubName(),
                                                                             provider.getCredential()))
        );
    }

}
