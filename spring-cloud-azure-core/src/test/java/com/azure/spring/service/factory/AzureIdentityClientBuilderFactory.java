package com.azure.spring.service.factory;

import com.azure.identity.implementation.IdentityClientBuilder;
import com.azure.identity.implementation.IdentityClientOptions;
import com.azure.spring.core.builder.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.client.descriptor.AzureClientOptionsDescriptor;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.service.factory.client.AzureIdentityClientOptionsDescriptor;
import org.springframework.boot.context.properties.PropertyMapper;

import java.util.List;

public class AzureIdentityClientBuilderFactory extends AbstractAzureHttpClientBuilderFactory<IdentityClientBuilder, IdentityClientOptions, Object> {

    private final IdentifyClientProperties properties;

    public AzureIdentityClientBuilderFactory(IdentifyClientProperties properties) {
        this.properties = properties;
    }

    @Override
    protected IdentityClientBuilder createBuilderInstance() {
        return new IdentityClientBuilder();
    }

    @Override
    protected void configureService(IdentityClientBuilder builder) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        map.from(properties.isSharedTokenCacheCred()).to(builder::sharedTokenCacheCredential);
        // Other items of the IdentifyClientProperties should be service level or core level configuration?
    }

    @Override
    protected AzureProperties getAzureProperties() {
        return null;
    }

    // TODO: Should we provide an empty implementation in super class?
    @Override
    protected List<AuthenticationDescriptor> getAuthenticationDescriptors(IdentityClientBuilder builder) {
        return null;
    }

    @Override
    protected AzureClientOptionsDescriptor<IdentityClientOptions> getAzureClientOptionsDescriptor(IdentityClientBuilder builder) {
        return new AzureIdentityClientOptionsDescriptor(client -> builder.identityClientOptions(client));
    }

    /**
     * Override the default implementation, only client options configuration for IdentifyClientBuilder in azure core level.
     * @param builder SDK client builder
     */
    @Override
    protected void configureCore(IdentityClientBuilder builder) {
        configureClientOptions(builder);
    }
}
