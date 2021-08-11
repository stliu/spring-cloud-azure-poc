package com.azure.spring.core.http;

import com.azure.core.http.ProxyOptions;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.HttpClientOptions;
import com.azure.spring.core.AzureServiceClientBuilderFactory;
import com.azure.spring.core.properties.ClientOptionsProperties;
import com.azure.spring.core.properties.http.HttpClientOptionsProperties;
import com.azure.spring.core.properties.http.HttpProperties;
import com.azure.spring.core.properties.http.HttpProxyOptionsProperties;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;

/**
 * Common http client builder factory of module-based http protocol.
 * @param <T> Actual SDK service client builder
 */
public interface AzureHttpClientBuilderFactory<T> extends AzureServiceClientBuilderFactory<T>, HttpPipelineAware {

//    List<HttpPipelinePolicy> getHttpPipelinePolicies();

    default HttpProperties getRootHttpProperties() {
        return null;
    }

    default HttpProperties getInheritHttpProperties() {
        return null;
    }

    default HttpPipelinePoliciesSupplier getHttpPipelinePolicySupplier() {
        return null;//new HttpPipelinePoliciesSupplierImpl(getRootHttpProperties(), getInheritHttpProperties(), supportFeatures());
    }

    default HttpClientSupplier getHttpClientSupplier() {
        return null;//new HttpClientSupplierImpl(getRootHttpProperties(), getInheritHttpProperties());
    }

    default AzureHttpClientBuilderFactory httpClientOptions(HttpClientOptions httpClientOptions) {
        invoke(defaultClientOptionsMethodName(), new Class[] {ClientOptions.class}, httpClientOptions);
        return this;
    }

    @Override
    default ClientOptions getClientOptions(ClientOptionsProperties client) {
        return getHttpClientOptions((HttpClientOptionsProperties) client);
    }

    default HttpClientOptions getHttpClientOptions(HttpClientOptionsProperties client) {
        HttpClientOptions httpClientOptions = null;
        if (client != null) {
            httpClientOptions = getHttpClientOptions(client);
            httpClientOptions.setProxyOptions(getProxyOptions(client.getProxy()));
            httpClientOptions.setConfiguration(getDefaultConfiguration());
        }
        return httpClientOptions;
    }
    default ProxyOptions getProxyOptions(HttpProxyOptionsProperties proxy) {
        InetSocketAddress address = new InetSocketAddress(proxy.getHost(), proxy.getPort());
        ProxyOptions proxyOptions = new ProxyOptions(proxy.getType(), address);
        if (StringUtils.hasText(proxy.getUsername())
            && StringUtils.hasText(proxy.getPassword())) {
            proxyOptions.setCredentials(proxy.getUsername(), proxy.getPassword());
        }
        return proxyOptions;
    }
}