package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.aware.credential.ConnectionStringAware;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.eventhub")
public class EventHubProperties extends AzureProperties implements ConnectionStringAware {

    private boolean isSharedConnection;
    private String namespace;
    private String eventHubName;
    private String connectionString;
    private String customEndpointAddress;
    private String consumerGroup;
    private Integer prefetchCount;

    public boolean isSharedConnection() {
        return isSharedConnection;
    }

    public void setSharedConnection(boolean sharedConnection) {
        isSharedConnection = sharedConnection;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getEventHubName() {
        return eventHubName;
    }

    public void setEventHubName(String eventHubName) {
        this.eventHubName = eventHubName;
    }

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getCustomEndpointAddress() {
        return customEndpointAddress;
    }

    public void setCustomEndpointAddress(String customEndpointAddress) {
        this.customEndpointAddress = customEndpointAddress;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public Integer getPrefetchCount() {
        return prefetchCount;
    }

    public void setPrefetchCount(Integer prefetchCount) {
        this.prefetchCount = prefetchCount;
    }
}
