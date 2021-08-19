package com.azure.spring.service.factory.client;

import com.azure.core.http.ProxyOptions;
import com.azure.identity.implementation.IdentityClientOptions;
import com.azure.spring.core.aware.HttpPropertiesAware;
import com.azure.spring.core.client.resolver.AzureClientOptionsResolver;
import com.azure.spring.core.properties.ClientProperties;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.spring.core.properties.http.HttpClientProperties;
import com.azure.spring.core.properties.http.HttpProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;

public class AzureIdentityClientOptionsResolver implements AzureClientOptionsResolver<IdentityClientOptions> {

    @Override
    public IdentityClientOptions resolve(ClientProperties properties) {
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

        IdentityClientOptions clientOptions = new IdentityClientOptions();
        //TODO:
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
