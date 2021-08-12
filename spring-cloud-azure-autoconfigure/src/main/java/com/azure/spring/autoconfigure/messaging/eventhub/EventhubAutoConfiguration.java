package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.messaging.eventhubs.EventHubConsumerClient;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.azure.spring.core.amqp.AzureSpringAmqpConfigurationContext;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(EventHubClientBuilder.class)
@ConditionalOnProperty(prefix = "spring.cloud.azure.eventhub", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(EventHub.class)
@AutoConfigureAfter
public class EventhubAutoConfiguration {

//    @Bean
//    @ConditionalOnMissingBean
//    public EventhubBuilderCustomizer eventhubBuilderCustomizer() {
//        return new EventhubBuilderCustomizer(){};
//    }

//    @Bean
//    @ConditionalOnMissingBean
//    public EventHubClientBuilder eventHubClientBuilder(EventHubProperties properties,
//        ObjectProvider<EventhubBuilderCustomizer> configurers) {
//        EventHubClientBuilder builder = new EventHubClientBuilder();
//
//        configurers.orderedStream().forEach(c -> c.customize(builder));
//        return builder;
//    }

    @Bean
    @ConditionalOnMissingBean
    public EventHubConsumerAsyncClient eventHubConsumerAsyncClient(EventHubClientBuilder builder) {
        return builder.buildAsyncConsumerClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public EventHubConsumerClient eventHubConsumerClient(EventHubClientBuilder builder) {
        return builder.buildConsumerClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public EventHubProducerAsyncClient eventHubProducerAsyncClient(EventHubClientBuilder builder) {
        return builder.buildAsyncProducerClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public EventHubProducerClient eventHubProducerClient(EventHubClientBuilder builder) {
        return builder.buildProducerClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public AzureEventHubClientBuilderFactory factory(AzureSpringAmqpConfigurationContext context,
                                                     EventHub properties) {
        return new AzureEventHubClientBuilderFactory(context, properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public EventHubClientBuilder blobClientBuilder(AzureEventHubClientBuilderFactory factory) {
        return factory.build();
    }
}
