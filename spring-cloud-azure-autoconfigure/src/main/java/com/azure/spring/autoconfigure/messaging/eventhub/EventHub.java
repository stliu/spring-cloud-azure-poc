package com.azure.spring.autoconfigure.messaging.eventhub;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.spring.core.aware.AmqpPropertiesAware;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.aware.ConnectionStringAware;
import com.azure.spring.core.properties.amqp.AmqpProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Pattern;

@ConfigurationProperties(prefix = "spring.cloud.azure.eventhub")
public class EventHub extends AzureProperties implements
    AmqpPropertiesAware, ConnectionStringAware {

    private AmqpProperties amqp;
    private String connectionString;
    private String eventHubName;
    private String customEndpointAddress;
    private Boolean isSharedConnection;
    private String fullyQualifiedNamespace;
    private String namespace;

    private AmqpTransportType transport;
    private String consumerGroup;
    private Integer prefetchCount;
//    private SslDomain.VerifyMode verifyMode;

    @Pattern(regexp = "^[a-z0-9]{3,24}$",
        message = "must be between 3 and 24 characters in length and use numbers and lower-case letters only")
    private String checkpointStorageAccount;
    private String checkpointAccessKey;
    private String checkpointContainer;

    public AmqpProperties getAmqp() {
        return amqp;
    }

    @Override
    public void setAmqp(AmqpProperties amqp) {
        this.amqp = amqp;
    }

    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getEventHubName() {
        return eventHubName;
    }

    public void setEventHubName(String eventHubName) {
        this.eventHubName = eventHubName;
    }

    public String getCustomEndpointAddress() {
        return customEndpointAddress;
    }

    public void setCustomEndpointAddress(String customEndpointAddress) {
        this.customEndpointAddress = customEndpointAddress;
    }

    public Boolean getSharedConnection() {
        return isSharedConnection;
    }

    public void setSharedConnection(Boolean sharedConnection) {
        isSharedConnection = sharedConnection;
    }

    public String getFullyQualifiedNamespace() {
        return fullyQualifiedNamespace;
    }

    public void setFullyQualifiedNamespace(String fullyQualifiedNamespace) {
        this.fullyQualifiedNamespace = fullyQualifiedNamespace;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public AmqpTransportType getTransport() {
        return transport;
    }

    public void setTransport(AmqpTransportType transport) {
        this.transport = transport;
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

    public String getCheckpointStorageAccount() {
        return checkpointStorageAccount;
    }

    public void setCheckpointStorageAccount(String checkpointStorageAccount) {
        this.checkpointStorageAccount = checkpointStorageAccount;
    }

    public String getCheckpointAccessKey() {
        return checkpointAccessKey;
    }

    public void setCheckpointAccessKey(String checkpointAccessKey) {
        this.checkpointAccessKey = checkpointAccessKey;
    }

    public String getCheckpointContainer() {
        return checkpointContainer;
    }

    public void setCheckpointContainer(String checkpointContainer) {
        this.checkpointContainer = checkpointContainer;
    }
}
