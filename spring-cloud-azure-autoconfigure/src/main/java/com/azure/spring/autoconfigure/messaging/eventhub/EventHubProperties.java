package com.azure.spring.autoconfigure.messaging.eventhub;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.eventhub")
public class EventHubProperties {

    private Integer prefetchCount;
}
