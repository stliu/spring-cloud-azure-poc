package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.core.credential.TokenCredential;
import com.azure.core.exception.AzureException;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.spring.core.AzureSpringConfigurationContext;
import com.azure.spring.core.amqp.AzureAmqpClientBuilderFactory;
import com.azure.spring.core.amqp.AzureSpringAmqpConfigurationContext;
import com.azure.spring.core.properties.amqp.AmqpProperties;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.Optional;

/**
 * Storage Blob Service client builder factory, it builds the storage blob client
 * according the configuration context and blob properties.
 */
public class AzureEventHubServiceClientBuilderFactory implements
    AzureAmqpClientBuilderFactory<EventHubClientBuilder> {

    private final AzureSpringAmqpConfigurationContext configurationContext;
    private final EventHubProperties eventHubProperties;

    private EventHubClientBuilder builder;

    public AzureEventHubServiceClientBuilderFactory(AzureSpringAmqpConfigurationContext configurationContext,
                                                    EventHubProperties eventHubProperties) {
        this.configurationContext = configurationContext;
        this.eventHubProperties = eventHubProperties;

        builder = new EventHubClientBuilder();
    }

    @Override
    public AzureSpringConfigurationContext getConfigurationContext() {
        return configurationContext;
    }

    @Override
    public EventHubClientBuilder build() {

        Optional.ofNullable(eventHubProperties.getConnectionString()).ifPresent(this::connectionString);
        clientOptions(getClientOptions(eventHubProperties.getAmqp().getClient()));

        if (StringUtils.hasText(eventHubProperties.getConnectionString())
            && StringUtils.hasText(eventHubProperties.getEventHubName())) {
            connectionString(eventHubProperties.getConnectionString(), eventHubProperties.getEventHubName());
        }
        builder.configuration(configurationContext.getAzureDefaultConfiguration());

        Optional.ofNullable(eventHubProperties.getCustomEndpointAddress()).ifPresent(this::customEndpointAddress);
        if (eventHubProperties.getSharedConnection()) {
            shareConnection();
        }
        if (StringUtils.hasText(eventHubProperties.getFullyQualifiedNamespace())
            && StringUtils.hasText(eventHubProperties.getEventHubName())) {
            credential(eventHubProperties.getFullyQualifiedNamespace(), eventHubProperties.getEventHubName(), getTokenCredential(eventHubProperties));
        }
        proxyOptions(getProxyOptions(eventHubProperties.getAmqp().getProxy()));
        retry(getAmqpRetryOptions(eventHubProperties.getAmqp().getRetry()));
        Optional.ofNullable(eventHubProperties.getTransport()).ifPresent(this::transportType);
        Optional.ofNullable(eventHubProperties.getConsumerGroup()).ifPresent(this::consumerGroup);
        Optional.ofNullable(eventHubProperties.getPrefetchCount()).ifPresent(this::prefetchCount);
        return builder;
    }

    @Override
    public EventHubClientBuilder builder() {
        return builder;
    }

    @Override
    public AmqpProperties getRootAmqpProperties() {
        return null;
    }

    @Override
    public AmqpProperties getInheritAmqpProperties() {
        return null;
    }

    /**
     * Sets the credential information given a connection string to the Event Hub instance.
     *
     * <p>
     * If the connection string is copied from the Event Hubs namespace, it will likely not contain the name to the
     * desired Event Hub, which is needed. In this case, the name can be added manually by adding {@literal
     * "EntityPath=EVENT_HUB_NAME"} to the end of the connection string. For example, "EntityPath=telemetry-hub".
     * </p>
     *
     * <p>
     * If you have defined a shared access policy directly on the Event Hub itself, then copying the connection string
     * from that Event Hub will result in a connection string that contains the name.
     * </p>
     *
     * @param connectionString The connection string to use for connecting to the Event Hub instance. It is expected
     *     that the Event Hub name and the shared access key properties are contained in this connection string.
     *
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     * @throws IllegalArgumentException if {@code connectionString} is null or empty. Or, the {@code
     *     connectionString} does not contain the "EntityPath" key, which is the name of the Event Hub instance.
     * @throws AzureException If the shared access signature token credential could not be created using the
     *     connection string.
     */
    public AzureEventHubServiceClientBuilderFactory connectionString(String connectionString) {
        builder.connectionString(connectionString);
        return this;
    }

    /**
     * Sets the client options.
     *
     * @param clientOptions The client options.
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     */
    public AzureEventHubServiceClientBuilderFactory clientOptions(ClientOptions clientOptions) {
        builder.clientOptions(clientOptions);
        return this;
    }

    /**
     * Sets the credential information given a connection string to the Event Hubs namespace and name to a specific
     * Event Hub instance.
     *
     * @param connectionString The connection string to use for connecting to the Event Hubs namespace; it is
     *     expected that the shared access key properties are contained in this connection string, but not the Event Hub
     *     name.
     * @param eventHubName The name of the Event Hub to connect the client to.
     *
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     * @throws NullPointerException if {@code connectionString} or {@code eventHubName} is null.
     * @throws IllegalArgumentException if {@code connectionString} or {@code eventHubName} is an empty string. Or,
     *     if the {@code connectionString} contains the Event Hub name.
     * @throws AzureException If the shared access signature token credential could not be created using the
     *     connection string.
     */
    public AzureEventHubServiceClientBuilderFactory connectionString(String connectionString, String eventHubName) {
        builder.connectionString(connectionString, eventHubName);
        return this;
    }

    /**
     * Sets the configuration store that is used during construction of the service client.
     *
     * If not specified, the default configuration store is used to configure the EventHubAsyncClient. Use
     * {@link Configuration#NONE} to bypass using configuration settings during construction.
     *
     * @param configuration The configuration store used to configure the EventHubAsyncClient.
     *
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     */
    public AzureEventHubServiceClientBuilderFactory configuration(Configuration configuration) {
        builder.configuration(configuration);
        return this;
    }

    /**
     * Sets a custom endpoint address when connecting to the Event Hubs service. This can be useful when your network
     * does not allow connecting to the standard Azure Event Hubs endpoint address, but does allow connecting through
     * an intermediary. For example: {@literal https://my.custom.endpoint.com:55300}.
     * <p>
     * If no port is specified, the default port for the {@link #transportType(AmqpTransportType) transport type} is
     * used.
     *
     * @param customEndpointAddress The custom endpoint address.
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     * @throws IllegalArgumentException if {@code customEndpointAddress} cannot be parsed into a valid {@link URL}.
     */
    public AzureEventHubServiceClientBuilderFactory customEndpointAddress(String customEndpointAddress) {
        builder.customEndpointAddress(customEndpointAddress);
        return this;
    }

    /**
     * Toggles the builder to use the same connection for producers or consumers that are built from this instance. By
     * default, a new connection is constructed and used created for each Event Hub consumer or producer created.
     *
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     */
    public AzureEventHubServiceClientBuilderFactory shareConnection() {
        builder.shareConnection();
        return this;
    }

    /**
     * Sets the credential information for which Event Hub instance to connect to, and how to authorize against it.
     *
     * @param fullyQualifiedNamespace The fully qualified name for the Event Hubs namespace. This is likely to be
     *     similar to <strong>{@literal "{your-namespace}.servicebus.windows.net}"</strong>.
     * @param eventHubName The name of the Event Hub to connect the client to.
     * @param credential The token credential to use for authorization. Access controls may be specified by the
     *     Event Hubs namespace or the requested Event Hub, depending on Azure configuration.
     *
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     * @throws IllegalArgumentException if {@code fullyQualifiedNamespace} or {@code eventHubName} is an empty
     *     string.
     * @throws NullPointerException if {@code fullyQualifiedNamespace}, {@code eventHubName}, {@code credentials} is
     *     null.
     */
    public AzureEventHubServiceClientBuilderFactory credential(String fullyQualifiedNamespace, String eventHubName,
                                                               TokenCredential credential) {
        builder.credential(fullyQualifiedNamespace, eventHubName, credential);
        return this;
    }

    /**
     * Sets the transport type by which all the communication with Azure Event Hubs occurs. Default value is {@link
     * AmqpTransportType#AMQP}.
     *
     * @param transport The transport type to use.
     *
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     */
    public AzureEventHubServiceClientBuilderFactory transportType(AmqpTransportType transport) {
        builder.transportType(transport);
        return this;
    }

    /**
     * Sets the name of the consumer group this consumer is associated with.
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     */
    public AzureEventHubServiceClientBuilderFactory consumerGroup(String consumerGroup) {
        builder.consumerGroup(consumerGroup);
        return this;
    }

    /**
     * Sets the count used by the receiver to control the number of events the Event Hub consumer will actively receive
     * and queue locally without regard to whether a receive operation is currently active.
     *
     * @param prefetchCount The amount of events to queue locally.
     *
     * @return The updated {@link AzureEventHubServiceClientBuilderFactory} object.
     * @throws IllegalArgumentException if {@code prefetchCount} is less than 1 or greater than 8000.
     */
    public AzureEventHubServiceClientBuilderFactory prefetchCount(int prefetchCount) {
        builder.prefetchCount(prefetchCount);
        return this;
    }
}
