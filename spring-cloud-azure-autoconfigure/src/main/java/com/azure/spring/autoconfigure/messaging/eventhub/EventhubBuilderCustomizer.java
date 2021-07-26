package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.spring.autoconfigure.core.ServiceClientBuilderCustomizer;

public interface EventhubBuilderCustomizer  extends ServiceClientBuilderCustomizer<EventHubClientBuilder> {

    @Override
    default void customize(EventHubClientBuilder builder) {

    }
}
