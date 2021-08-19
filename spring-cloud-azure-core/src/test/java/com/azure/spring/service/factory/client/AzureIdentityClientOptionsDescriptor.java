package com.azure.spring.service.factory.client;

import com.azure.identity.implementation.IdentityClientOptions;
import com.azure.spring.core.client.descriptor.AzureClientOptionsDescriptor;
import com.azure.spring.core.client.resolver.AzureClientOptionsResolver;

import java.util.function.Consumer;

public class AzureIdentityClientOptionsDescriptor implements AzureClientOptionsDescriptor<IdentityClientOptions> {

    private final Consumer<IdentityClientOptions> consumer;

    public AzureIdentityClientOptionsDescriptor(Consumer<IdentityClientOptions> consumer) {
        this.consumer = consumer;
    }

    @Override
    public AzureClientOptionsResolver<IdentityClientOptions> clientOptionsResolver() {
        return new AzureIdentityClientOptionsResolver();
    }

    @Override
    public Consumer<IdentityClientOptions> consumer() {
        return consumer;
    }
}
