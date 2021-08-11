package com.azure.spring.core.amqp;

import com.azure.core.amqp.AmqpRetryOptions;
import com.azure.core.amqp.ProxyOptions;
import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.AzureServiceClientBuilderFactory;
import com.azure.spring.core.properties.amqp.AmqpProperties;
import com.azure.spring.core.properties.amqp.AmqpProxyOptionsProperties;
import com.azure.spring.core.properties.amqp.AmqpRetryOptionsProperties;

import java.net.InetSocketAddress;
import java.net.Proxy;

public interface AzureAmqpClientBuilderFactory<T> extends AzureServiceClientBuilderFactory<T> {

    String PROXY_OPTIONS_METHOD_NAME = "proxyOptions";
    String AMQP_RETRY_OPTIONS_METHOD_NAME = "retry";

    default AmqpProperties getRootAmqpProperties() {
        return null;
    }

    default AmqpProperties getInheritAmqpProperties() {
        return null;
    }

    default String defaultProxyOptionsMethodName() {
        return PROXY_OPTIONS_METHOD_NAME;
    }

    default String defaultAmqpRetryOptionsMethodName() {
        return AMQP_RETRY_OPTIONS_METHOD_NAME;
    }

    default AzureServiceClientBuilderFactory proxyOptions(ProxyOptions proxyOptions) {
        invoke(defaultProxyOptionsMethodName(), new Class[] { ProxyOptions.class}, proxyOptions);
        return this;
    }

    default AzureServiceClientBuilderFactory retry(AmqpRetryOptions retryOptions) {
        invoke(defaultAmqpRetryOptionsMethodName(), new Class[] { AmqpRetryOptions.class}, retryOptions);
        return this;
    }

    default AmqpRetryOptions getAmqpRetryOptions(AmqpRetryOptionsProperties retryOptions) {
        AmqpRetryOptions amqpRetryOptions = new AmqpRetryOptions();
        amqpRetryOptions.setDelay(retryOptions.getDelay());
        amqpRetryOptions.setMaxRetries(retryOptions.getMaxRetries());
        amqpRetryOptions.setMode(retryOptions.getRetryMode());
        amqpRetryOptions.setMaxDelay(retryOptions.getMaxDelay());
        amqpRetryOptions.setTryTimeout(retryOptions.getTryTimeout());
        return amqpRetryOptions;
    }

    default ProxyOptions getProxyOptions(AmqpProxyOptionsProperties proxy) {
        InetSocketAddress address = new InetSocketAddress(proxy.getHost(), proxy.getPort());
        Proxy proxyAddress = new Proxy(proxy.getType(), address);
        ProxyOptions proxyOptions = new ProxyOptions(proxy.getProxyAuthenticationType(),
            proxyAddress, proxy.getUsername(), proxy.getPassword());
        return proxyOptions;
    }
}