package com.azure.spring.core.client.resolver;

import com.azure.core.http.ProxyOptions;
import com.azure.core.util.Header;
import com.azure.core.util.HttpClientOptions;
import com.azure.spring.core.aware.HttpPropertiesAware;
import com.azure.spring.core.properties.ClientProperties;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.spring.core.properties.http.HttpClientProperties;
import com.azure.spring.core.properties.http.HttpProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AzureHttpClientOptionsResolver implements AzureClientOptionsResolver<HttpClientOptions> {

    @Override
    public HttpClientOptions resolve(ClientProperties properties) {
        if (!isResolvable(properties)) {
            return null;
        }

        HttpProperties http = ((HttpPropertiesAware) properties).getHttp();
        if (http == null) {
            return null;
        }

        HttpClientProperties client = http.getClient();
        if (client == null) {
            return null;
        }

        if (!StringUtils.hasText(client.getApplicationId())
            && CollectionUtils.isEmpty(client.getHeaders())
            && client.getRetry() == null
            && client.getHeaders() == null) {
            return null;
        }

        HttpClientOptions clientOptions = new HttpClientOptions();
        Optional.ofNullable(client.getProxy())
                .map(this::convertToProxyOptions)
                .ifPresent(clientOptions::setProxyOptions);
        Optional.ofNullable(client.getApplicationId())
                .ifPresent(clientOptions::setApplicationId);
        Optional.ofNullable(client.getHeaders()).ifPresent(headerProperties -> {
            List<Header> headers = new ArrayList<>();
            headerProperties.forEach(headerProp -> {
                headers.add(new Header(headerProp.getName(), headerProp.getValues()));
            });
            clientOptions.setHeaders(headers);
        });
        return clientOptions;
    }

    private ProxyOptions convertToProxyOptions(ProxyProperties properties) {
        InetSocketAddress address = new InetSocketAddress(properties.getHostname(), properties.getPort());
        ProxyOptions proxyOptions = new ProxyOptions(getProxyType(properties), address);
        if (StringUtils.hasText(properties.getUsername())
            && StringUtils.hasText(properties.getPassword())) {
            proxyOptions.setCredentials(properties.getUsername(), properties.getPassword());
        }
        return proxyOptions;
    }

    private ProxyOptions.Type getProxyType(ProxyProperties properties) {
        // TODO: convert the type
        return ProxyOptions.Type.HTTP;
    }

    @Override
    public boolean isResolvable(ClientProperties properties) {
        return properties instanceof HttpPropertiesAware;
    }
}
