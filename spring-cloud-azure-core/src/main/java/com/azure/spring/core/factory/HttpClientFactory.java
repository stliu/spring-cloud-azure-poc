package com.azure.spring.core.factory;

import com.azure.core.http.HttpClient;
import com.azure.core.http.ProxyOptions;
import com.azure.core.util.Header;
import com.azure.core.util.HttpClientOptions;
import com.azure.spring.core.properties.ProxyProperties;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.List;

public class HttpClientFactory {

    HttpClientOptions httpClientOptions = new HttpClientOptions();

    public HttpClientFactory() {
    }

    public void configureProxy(ProxyProperties proxyProperties) {
        if (proxyProperties == null) {
            return;
        }
        this.httpClientOptions.setProxyOptions(getProxyOptions(proxyProperties));
    }

    public void configureApplicationId(String applicationId) {
        this.httpClientOptions.setApplicationId(applicationId);
    }

    public void configureReadTimeout(Duration readTimeout) {
        this.httpClientOptions.readTimeout(readTimeout);
    }

    public void configureWriteTimeout(Duration writeTimeout) {
        this.httpClientOptions.setWriteTimeout(writeTimeout);
    }

    public void configureResponseTimeout(Duration responseTimeout) {
        this.httpClientOptions.responseTimeout(responseTimeout);
    }

    public void configureHttpHeaders(List<Header> headers) {
        this.httpClientOptions.setHeaders(headers);
    }

    public HttpClient build() {
        return HttpClient.createDefault(this.httpClientOptions);
    }

    private ProxyOptions getProxyOptions(ProxyProperties proxyProperties) {
        final String type = proxyProperties.getType();
        ProxyOptions.Type sdkProxyType;
        if ("http".equalsIgnoreCase(type)) {
            sdkProxyType = ProxyOptions.Type.HTTP;
        } else {
            sdkProxyType = ProxyOptions.Type.SOCKS4;
        }

        ProxyOptions proxyOptions = new ProxyOptions(sdkProxyType, new InetSocketAddress(proxyProperties.getHostname(),
                                                                                         proxyProperties.getPort()));
        if (StringUtils.hasText(proxyProperties.getUsername()) && StringUtils.hasText(proxyProperties.getPassword())) {
            proxyOptions.setCredentials(proxyProperties.getUsername(), proxyProperties.getPassword());
        }
        // TODO non proxy hosts
        return proxyOptions;
    }

}
