package com.azure.spring.core.properties.amqp;

import com.azure.core.amqp.AmqpRetryMode;

import java.time.Duration;

public class AmqpRetryOptionsProperties {

    private int maxRetries;
    private Duration delay;
    private Duration maxDelay;
    private Duration tryTimeout;
    private AmqpRetryMode retryMode;

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

    public AmqpRetryMode getRetryMode() {
        return retryMode;
    }

    public void setRetryMode(AmqpRetryMode retryMode) {
        this.retryMode = retryMode;
    }
}
