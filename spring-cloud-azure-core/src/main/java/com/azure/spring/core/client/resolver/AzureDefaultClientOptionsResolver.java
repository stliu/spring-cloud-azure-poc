package com.azure.spring.core.client.resolver;

import com.azure.core.util.ClientOptions;
import com.azure.spring.core.aware.client.ClientAware;
import com.azure.spring.core.properties.ClientProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class AzureDefaultClientOptionsResolver implements AzureClientOptionsResolver<ClientOptions> {

    @Override
    public ClientOptions resolve(ClientProperties properties) {
        if (!isResolvable(properties)) {
            return null;
        }

        ClientProperties client = ((ClientAware) properties).getClient();
        if (client == null) {
            return null;
        }

        if (!StringUtils.hasText(client.getApplicationId())
            && CollectionUtils.isEmpty(client.getHeaders())) {
            return null;
        }

        ClientOptions clientOptions = new ClientOptions();
        clientOptions.setApplicationId(client.getApplicationId());
        clientOptions.setHeaders(clientOptions.getHeaders());
        return clientOptions;
    }

    @Override
    public boolean isResolvable(ClientProperties properties) {
        return properties instanceof ClientAware;
    }
}
