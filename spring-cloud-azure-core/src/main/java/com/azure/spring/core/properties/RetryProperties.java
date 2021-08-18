package com.azure.spring.core.properties;

import java.time.Duration;

/**
 * Common retry properties for all Azure SDKs.
 */
public class RetryProperties {

    private int maxRetries;
    private Duration retryDelay;
    private Duration maxRetryDelay;
    private Duration tryTimeout;
    private AzureRetryType type;

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Duration getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(Duration retryDelay) {
        this.retryDelay = retryDelay;
    }

    public Duration getMaxRetryDelay() {
        return maxRetryDelay;
    }

    public void setMaxRetryDelay(Duration maxRetryDelay) {
        this.maxRetryDelay = maxRetryDelay;
    }

    public Duration getTryTimeout() {
        return tryTimeout;
    }

    public void setTryTimeout(Duration tryTimeout) {
        this.tryTimeout = tryTimeout;
    }

    public AzureRetryType getRetryType() {
        return type;
    }

    public void setRetryType(AzureRetryType azureRetryType) {
        this.type = azureRetryType;
    }
}
