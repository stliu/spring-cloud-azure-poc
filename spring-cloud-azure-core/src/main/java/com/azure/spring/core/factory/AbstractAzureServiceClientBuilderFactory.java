package com.azure.spring.core.factory;

import com.azure.core.util.Header;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.provider.AzureCredentialProvider;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureCredentialResolvers;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Abstract azure service client builder factory, it's the implementation of {@link AzureServiceClientBuilderFactory} to
 * provide the template methods to extend any service on the top of azure core.
 *
 * @param <T> Service client builder
 */
public abstract class AbstractAzureServiceClientBuilderFactory<T> implements AzureServiceClientBuilderFactory<T> {

    private static Logger LOGGER = LoggerFactory.getLogger(AbstractAzureServiceClientBuilderFactory.class);

    protected abstract T createBuilderInstance();

    protected abstract AzureProperties getAzureProperties();

    protected abstract List<AuthenticationDescriptor<?>> getAuthenticationDescriptors(T builder);

    protected abstract void configureApplicationId(T builder, String applicationId);

    protected abstract void configureHeaders(T builder, List<Header> headers);

    protected abstract void configureClient(T builder);

    protected abstract void configureProxy(T builder);

    protected abstract void configureRetry(T builder);

    protected abstract void configureService(T builder);

    protected void customizeBuilder(T builder) { }

    /**
     * 1. create a builder instance
     * 2. configure builder
     *   2.1 configure azure core level configuration
     *     2.1.1 configure http client getHttpClientInstance
     *   2.2 configure service level configuration
     * 3. customize builder
     * 4. return builder
     */
    public T build() {
        T builder = createBuilderInstance();
        configureCore(builder);
        configureService(builder);
        customizeBuilder(builder);
        return builder;
    }

    protected void configureCore(T builder) {
        configureApplicationId(builder, getApplicationId());
        configureHeaders(builder, getHeaders());
        configureProxy(builder);
        configureRetry(builder);
        configureCredential(builder);
        configureClient(builder);
    }


    protected void configureCredential(T builder) {
        List<AuthenticationDescriptor<?>> authenticationDescriptors = getAuthenticationDescriptors(builder);
        AzureCredentialProvider<?> azureCredentialProvider = resolveAzureCredential(getAzureProperties(),
                                                                                    authenticationDescriptors);
        configureCredential(azureCredentialProvider, authenticationDescriptors);
    }

    protected String getApplicationId() {
        final ClientProperties client = getAzureProperties().getClient();
        return client == null ? null : client.getApplicationId();
    }

    protected List<Header> getHeaders() {
        final ClientProperties client = getAzureProperties().getClient();
        if (client == null || client.getHeaders() == null) return null;
        return client.getHeaders()
                     .stream()
                     .map(h -> new Header(h.getName(), h.getValues()))
                     .collect(Collectors.toList());
    }

    private AzureCredentialProvider<?> resolveAzureCredential(AzureProperties azureProperties,
                                                              List<AuthenticationDescriptor<?>> descriptors) {
        List<AzureCredentialResolver<?>> resolvers = descriptors.stream()
                                                                .map(AuthenticationDescriptor::azureCredentialResolver)
                                                                .collect(Collectors.toList());
        AzureCredentialResolvers credentialResolvers = new AzureCredentialResolvers(resolvers);
        return credentialResolvers.resolve(azureProperties);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void configureCredential(AzureCredentialProvider provider, List<AuthenticationDescriptor<?>> descriptors) {
        final Consumer consumer = descriptors.stream()
                                             .filter(d -> d.azureCredentialType() == provider.getType())
                                             .map(AuthenticationDescriptor::consumer)
                                             .findFirst()
                                             .orElseThrow(
                                                 () -> new IllegalArgumentException("Consumer should not be null"));


        consumer.accept(provider.getCredential());
    }
}
