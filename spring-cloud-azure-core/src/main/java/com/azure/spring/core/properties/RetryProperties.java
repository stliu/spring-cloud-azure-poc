package com.azure.spring.core.properties;

import java.time.Duration;

/**
 * Common retry properties for all Azure SDKs.
 */
public class RetryProperties {

    private String mode;
    private Duration delay;
    private Duration maxDelay;
    private int maxRetries;
    private Duration tryTimeout;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Duration getDelay() {
        return delay;
    }

    public void setDelay(Duration delay) {
        this.delay = delay;
    }

    public Duration getMaxDelay() {
        return maxDelay;
    }

    public void setMaxDelay(Duration maxDelay) {
        this.maxDelay = maxDelay;
    }

    public Duration getTryTimeout() {
        return tryTimeout;
    }

    public void setTryTimeout(Duration tryTimeout) {
        this.tryTimeout = tryTimeout;
    }
}
