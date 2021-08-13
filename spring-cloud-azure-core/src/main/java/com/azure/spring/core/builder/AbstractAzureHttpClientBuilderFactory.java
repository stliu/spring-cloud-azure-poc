package com.azure.spring.core.builder;

import com.azure.core.http.ProxyOptions;
import com.azure.core.util.Header;
import com.azure.core.util.HttpClientOptions;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.spring.core.properties.http.HttpClientOptionsProperties;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractAzureHttpClientBuilderFactory<T> extends AbstractAzureServiceClientBuilderFactory<T> {

    @Override
    protected void configureProxy(T builder) {

    }

    private ProxyOptions convertToProxyOptions(ProxyProperties properties) {
        InetSocketAddress address = new InetSocketAddress(properties.getHostname(), properties.getPort());
        ProxyOptions proxyOptions = new ProxyOptions(getProxyType(properties), address);
        if (StringUtils.hasText(properties.getUsername()) && StringUtils.hasText(properties.getPassword())) {
            proxyOptions.setCredentials(properties.getUsername(), properties.getPassword());
        }
        return proxyOptions;
    }

    private ProxyOptions.Type getProxyType(ProxyProperties properties) {
        // TODO
        return ProxyOptions.Type.HTTP;
    }

    @Override
    protected void configureClientOptions(T builder) {
        HttpClientOptions httpClientOptions = new HttpClientOptions();
        HttpClientOptionsProperties client = null; // (HttpClientOptionsProperties) getClientProperties();
        Optional.ofNullable(client.getProxy()).map(this::convertToProxyOptions).ifPresent(httpClientOptions::setProxyOptions);
        Optional.ofNullable(client.getApplicationId()).ifPresent(httpClientOptions::setApplicationId);
        Optional.ofNullable(client.getHeaders()).ifPresent(headerProperties -> {
            List<Header> headers = new ArrayList<>();
            headerProperties.forEach(headerProp -> {
                headers.add(new Header(headerProp.getName(), headerProp.getValues()));
            });
            httpClientOptions.setHeaders(headers);
        });
//        httpClientOptions.setConfiguration(getConfiguration());
        // TODO: set clientOptions
    }
}
