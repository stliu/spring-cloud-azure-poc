package com.azure.spring.core.builder;

import com.azure.spring.core.aware.client.ClientAware;
import com.azure.spring.core.client.descriptor.AzureClientOptionsDescriptor;
import com.azure.spring.core.credential.descriptor.AuthenticationDescriptor;
import com.azure.spring.core.credential.provider.AzureCredentialProvider;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureCredentialResolvers;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractAzureServiceClientBuilderFactory<T, O, R> implements AzureServiceClientBuilderFactory<T> {

    private static Logger LOGGER = LoggerFactory.getLogger(AbstractAzureServiceClientBuilderFactory.class);

    /**
     * Create client builder
     * @return builder instance
     */
    protected abstract T createBuilderInstance();

    protected ClientProperties getClientProperties() {
        AzureProperties azureProperties = getAzureProperties();
        if (azureProperties instanceof ClientAware) {
            return ((ClientAware) azureProperties).getClient();
        }

        return null;
    }

    protected void configureClientOptions(T builder) {
        AzureClientOptionsDescriptor<O> descriptor = getAzureHttpClientOptionsDescriptor();
        if (descriptor == null) {
            if (getClientProperties() != null) {
                // TODO: Throw exception or only warn log?
                LOGGER.warn("Not found 'AzureHttpClientOptionsDescriptor' implementation, please override the "
                    + "method 'AbstractAzureServiceClientBuilderFactory.getAzureHttpClientOptionsDescriptor'");
            }
            return;
        }

        O client = descriptor.clientOptionsResolver().resolve(getClientProperties());
        if (client != null) {
            descriptor.consumer().accept(client);
        }
    }

    /**
     * Empty implementation.
     * Add {@link AzureClientOptionsDescriptor} implementation if you need ClientOptions personalized configuration.
     * @return
     */
    protected AzureClientOptionsDescriptor<O> getAzureHttpClientOptionsDescriptor() {
        return null;
    }

    protected abstract void configureProxy(T builder);

    /**
     * Configure SDK Service level configuration,
     * @param builder SDK client builder
     */
    protected abstract void configureService(T builder);

    /**
     * TODO: add description
     * @param builder
     */
    protected void customizeBuilder(T builder) {

    }

    protected abstract AzureProperties getAzureProperties();

    /**
     * Get the authentication descriptors
     * @param builder SDK client builder
     * @return descriptor collection
     */
    protected abstract List<AuthenticationDescriptor> getAuthenticationDescriptors(T builder);

    /**
     1. create a builder instance
     2. configure builder
       2.1 configure azure core level configuration
         2.1.1 configure http client
            getHttpClientInstance
       2.2 configure service level configuration
     3. customize builder
     4. return builder
     */
    public T build() {
        T builder = createBuilderInstance();
        configureCore(builder);
        configureService(builder);
        customizeBuilder(builder);
        return builder;
    }

    /**
     * Configure Azure Core level configuration
     * @param builder SDK client builder
     */
    protected void configureCore(T builder) {
        configureClientOptions(builder);
        configureProxy(builder);
        List<AuthenticationDescriptor> authenticationDescriptors = getAuthenticationDescriptors(builder);
        AzureCredentialProvider azureCredentialProvider = resolveAzureCredential(getAzureProperties(), authenticationDescriptors);
        configureCredential(azureCredentialProvider, authenticationDescriptors);
    }

    private AzureCredentialProvider resolveAzureCredential(AzureProperties azureProperties,
                                                           List<AuthenticationDescriptor> descriptors) {
        List<AzureCredentialResolver<?>> resolvers = descriptors.stream()
                                                                .map(d -> d.azureCredentialResolver())
                                                                .collect(Collectors.toList());
        AzureCredentialResolvers credentialResolvers = new AzureCredentialResolvers(resolvers);
        return credentialResolvers.resolve(azureProperties);
    }

    /**
     * Configure credential
     * @param provider
     * @param descriptors
     */
    private void configureCredential(AzureCredentialProvider provider, List<AuthenticationDescriptor> descriptors) {
        Consumer consumer = descriptors.stream()
                                       .filter(d -> d.azureCredentialType() == provider.getType())
                                       .map(d -> d.consumer())
                                       .findFirst()
                                       .get();
        consumer.accept(provider.getCredential());
    }
}
