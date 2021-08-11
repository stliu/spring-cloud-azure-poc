package com.azure.spring.core.http;

import com.azure.core.http.HttpClient;
import com.azure.core.implementation.http.HttpClientProviders;
import com.azure.core.util.Header;
import com.azure.core.util.HttpClientOptions;
import com.azure.spring.core.properties.http.HttpClientOptionsProperties;
import com.azure.spring.core.properties.http.HttpProperties;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Default implementation to supply the HttpClient.
 * Combine the http client provider and service client builder factory.
 */
public class HttpClientSupplierImpl implements HttpClientSupplier {

    private final HttpProperties rootProperties;
    private final HttpProperties inheritProperties;
    public HttpClientSupplierImpl(HttpProperties rootProperties,
                                  HttpProperties inheritProperties) {
        this.rootProperties = rootProperties;
        this.inheritProperties = inheritProperties;
    }

    private HttpClient httpClient;

    @Override
    public HttpClient get() {
        if (inheritProperties != null || rootProperties != null) {
            HttpClientOptions clientOptions = new HttpClientOptions();
            if (inheritProperties.getClient() != null) {
                buildClientOptions(clientOptions, inheritProperties.getClient());
            } else {
                buildClientOptions(clientOptions, rootProperties.getClient());
            }
            httpClient = HttpClientProviders.createInstance(clientOptions);
        } else {
            httpClient = HttpClient.createDefault();
        }
        return httpClient;
    }

    private void buildClientOptions(HttpClientOptions clientOptions, HttpClientOptionsProperties client) {
        if (StringUtils.hasText(client.getApplicationId())) {
            clientOptions.setApplicationId(client.getApplicationId());

        }
        List<Header> headers = new ArrayList<>();
        Optional.ofNullable(client.getHeaders())
                .ifPresent(headerProperties -> headerProperties.forEach(headerProperty -> {
                    Header header = new Header(headerProperty.getName(), headerProperty.getValues());
                    headers.add(header);
                }));
        clientOptions.setHeaders(headers);
    }
}
