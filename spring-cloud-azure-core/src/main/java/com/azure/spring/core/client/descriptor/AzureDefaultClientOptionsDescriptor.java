package com.azure.spring.core.client.descriptor;

import com.azure.core.util.ClientOptions;
import com.azure.spring.core.client.resolver.AzureClientOptionsResolver;
import com.azure.spring.core.client.resolver.AzureDefaultClientOptionsResolver;

import java.util.function.Consumer;

public class AzureDefaultClientOptionsDescriptor implements AzureClientOptionsDescriptor<ClientOptions> {

    private final Consumer<ClientOptions> consumer;

    public AzureDefaultClientOptionsDescriptor(Consumer<ClientOptions> consumer) {
        this.consumer = consumer;
    }

    @Override
    public AzureClientOptionsResolver<ClientOptions> clientOptionsResolver() {
        return new AzureDefaultClientOptionsResolver();
    }

    @Override
    public Consumer<ClientOptions> consumer() {
        return consumer;
    }
}
