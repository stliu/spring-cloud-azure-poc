package com.azure.spring.core.http.impl;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpClientProvider;
import com.azure.core.util.HttpClientOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringWebClientHttpClientProvider implements HttpClientProvider {
    private final static Logger log = LoggerFactory.getLogger(SpringWebClientHttpClientProvider.class);
    @Override
    public HttpClient createInstance() {
        return createInstance(null);
    }

    @Override
    public HttpClient createInstance(HttpClientOptions clientOptions) {
        log.info("Creating Azure SDK Http Client with options {}", clientOptions);
        SpringWebClientHttpClientBuilder builder = new SpringWebClientHttpClientBuilder();

        if (clientOptions != null) {
            builder = builder.proxy(clientOptions.getProxyOptions())
                .configuration(clientOptions.getConfiguration())
                .writeTimeout(clientOptions.getWriteTimeout())
                .responseTimeout(clientOptions.getResponseTimeout())
                .readTimeout(clientOptions.getReadTimeout());
        }

        return builder.build();
    }
}
