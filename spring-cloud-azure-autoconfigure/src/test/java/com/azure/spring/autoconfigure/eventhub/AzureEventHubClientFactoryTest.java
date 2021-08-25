package com.azure.spring.autoconfigure.eventhub;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.identity.ClientCertificateCredential;
import com.azure.identity.ClientSecretCredential;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.spring.autoconfigure.AzureServiceClientBuilderFactoryTestBase;
import com.azure.spring.core.properties.client.AmqpClientProperties;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public class AzureEventHubClientFactoryTest
    extends AzureServiceClientBuilderFactoryTestBase<EventHubClientBuilder, AzureEventHubProperties,
                                                        EventHubClientBuilderFactory> {

    // https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-get-connection-string
    // Endpoint=sb://<FQDN>/;SharedAccessKeyName=<KeyName>;SharedAccessKey=<KeyValue>
    private static final String CONNECTION_STRING = "Endpoint=sb://dummynamespace.servicebus.windows.net/;"
                                                        + "SharedAccessKeyName=DummyAccessKeyName;"
                                                        + "SharedAccessKey=5dOntTRytoC24opYThisAsit3is2B+OGY1USfuL3ly=";

    private static final String EVENTHUB_NAMESPACE = "test-namespace";
    private static final String FQDN = EVENTHUB_NAMESPACE + ".servicebus.windows.net";
    private static final String EVENTHUB_NAME = "test-eventhub";
    private static final String CONSUMER_GROUP = "$Default";

    @Test
    public void testMinimalSettings() {
        AzureEventHubProperties properties = createMinimalServiceProperties();
        properties.setCredential(buildClientCertificateTokenCredentialProperties());

        final EventHubClientBuilder clientBuilder = new EventHubClientBuilderFactory(properties).build();
        clientBuilder.buildConsumerClient();
        clientBuilder.buildProducerClient();
    }

    @Test
    public void testClientSecretTokenCredentialConfigured() {
        AzureEventHubProperties properties = createMinimalServiceProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientSecretTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final EventHubClientBuilder builder = new EventHubClientBuilderFactoryExt(properties).build();

        verify(builder, times(1)).credential(eq(FQDN), eq(EVENTHUB_NAME), any(ClientSecretCredential.class));
    }

    @Test
    public void testClientCertificateTokenCredentialConfigured() {
        AzureEventHubProperties properties = createMinimalServiceProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientCertificateTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final EventHubClientBuilder builder = new EventHubClientBuilderFactoryExt(properties).build();
        verify(builder, times(1)).credential(eq(FQDN), eq(EVENTHUB_NAME), any(ClientCertificateCredential.class));
    }

    @Test
    public void testAmqpTransportTypeConfigured() {
        AzureEventHubProperties properties = createMinimalServiceProperties();
        properties.setCredential(buildClientCertificateTokenCredentialProperties());

        final AmqpClientProperties clientProperties = new AmqpClientProperties();
        clientProperties.setTransportType(AmqpTransportType.AMQP);
        properties.setClient(clientProperties);

        final EventHubClientBuilder builder = new EventHubClientBuilderFactoryExt(properties).build();
        verify(builder, times(1)).transportType(AmqpTransportType.AMQP);
    }

    @Override
    protected AzureEventHubProperties createMinimalServiceProperties() {
        final AzureEventHubProperties properties = new AzureEventHubProperties();
        properties.setNamespace(EVENTHUB_NAMESPACE);
        properties.setEventHubName(EVENTHUB_NAME);
        properties.setConsumerGroup(CONSUMER_GROUP);
        return properties;
    }

    static class EventHubClientBuilderFactoryExt extends EventHubClientBuilderFactory {

        public EventHubClientBuilderFactoryExt(AzureEventHubProperties eventHubProperties) {
            super(eventHubProperties);
        }

        @Override
        protected EventHubClientBuilder createBuilderInstance() {
            return mock(EventHubClientBuilder.class);
        }
    }
}
