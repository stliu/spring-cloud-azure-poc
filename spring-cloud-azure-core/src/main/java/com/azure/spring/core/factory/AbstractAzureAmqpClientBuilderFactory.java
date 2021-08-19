package com.azure.spring.core.factory;

import com.azure.core.amqp.AmqpRetryMode;
import com.azure.core.amqp.AmqpRetryOptions;
import com.azure.core.amqp.AmqpTransportType;
import com.azure.core.amqp.ProxyAuthenticationType;
import com.azure.core.amqp.ProxyOptions;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Header;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.spring.core.properties.RetryProperties;
import com.azure.spring.core.properties.client.AmqpClientProperties;
import org.springframework.lang.NonNull;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

public abstract class AbstractAzureAmqpClientBuilderFactory<T> extends AbstractAzureServiceClientBuilderFactory<T> {

    private final ClientOptions clientOptions = new ClientOptions();

    protected abstract void configureAmqpProxy(T builder, ProxyOptions proxyOptions);
    
    protected abstract void configureAmqpTransportType(T builder, AmqpTransportType amqpTransportType);
    
    protected abstract void configureAmqpRetryOptions(T builder, AmqpRetryOptions amqpRetryOptions);

    @Override
    protected void configureClient(T builder) {
        final AmqpClientProperties client = (AmqpClientProperties) getAzureProperties().getClient();
        if (client == null) {
            return;
        }
        configureAmqpTransportType(builder, client.getTransportType());
    }

    @Override
    protected void configureApplicationId(T builder, String applicationId) {
        this.clientOptions.setApplicationId(applicationId);
    }

    @Override
    protected void configureHeaders(T builder, List<Header> headers) {
        this.clientOptions.setHeaders(headers);
    }

    @Override
    protected void configureRetry(T builder) {
        final RetryProperties retry = getAzureProperties().getRetry();
        if (retry == null) {
            return;
        }
        AmqpRetryOptions retryOptions = getAmqpRetryOptions(retry);
        configureAmqpRetryOptions(builder, retryOptions);
    }

    @Override
    protected void configureProxy(T builder) {
        if (getAzureProperties().getProxy() == null) {
            return;
        }
        final ProxyOptions proxyOptions = getProxyOptions(getAzureProperties().getProxy());
        configureAmqpProxy(builder, proxyOptions);
    }

    private AmqpRetryOptions getAmqpRetryOptions(RetryProperties retry) {
        AmqpRetryMode mode;
        if ("exponential".equals(retry.getMode())) {
            mode = AmqpRetryMode.EXPONENTIAL;
        } else {
            mode = AmqpRetryMode.FIXED;
        }
        AmqpRetryOptions retryOptions = new AmqpRetryOptions();
        retryOptions.setDelay(retry.getDelay());
        retryOptions.setMaxDelay(retry.getMaxDelay());
        retryOptions.setMode(mode);
        retryOptions.setMaxRetries(retry.getMaxRetries());
        retryOptions.setTryTimeout(retry.getTryTimeout());
        return retryOptions;
    }

    private ProxyOptions getProxyOptions(@NonNull ProxyProperties properties) {
        ProxyAuthenticationType authenticationType;
        switch (properties.getAuthenticationType()) {
            case "basic":
                authenticationType = ProxyAuthenticationType.BASIC;
                break;
            case "digest":
                authenticationType = ProxyAuthenticationType.DIGEST;
                break;
            default:
                authenticationType = ProxyAuthenticationType.NONE;
        }
        Proxy.Type type;
        switch (properties.getType()) {
            case "http":
                type = Proxy.Type.HTTP;
                break;
            case "socks":
                type = Proxy.Type.SOCKS;
                break;
            default:
                type = Proxy.Type.DIRECT;
        }
        Proxy proxyAddress = new Proxy(type, new InetSocketAddress(properties.getHostname(), properties.getPort()));
        return new ProxyOptions(authenticationType, proxyAddress, properties.getUsername(), properties.getPassword());
    }

}
