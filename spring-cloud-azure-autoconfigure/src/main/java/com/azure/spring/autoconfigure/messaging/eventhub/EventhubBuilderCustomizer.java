package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.spring.autoconfigure.core.ServiceClientBuilderCustomizer;

public class EventhubBuilderCustomizer  implements ServiceClientBuilderCustomizer<EventHubClientBuilder> {

    @Override
    public void customize(EventHubClientBuilder builder) {

    }
}
