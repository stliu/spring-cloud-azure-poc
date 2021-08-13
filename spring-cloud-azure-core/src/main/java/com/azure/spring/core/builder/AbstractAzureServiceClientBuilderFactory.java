package com.azure.spring.core.builder;

import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.AzureCredentialManager;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureKeyCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureNamedKeyCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureSasCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureTokenCredentialResolver;
import com.azure.spring.core.properties.AzureProperties;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAzureServiceClientBuilderFactory<T> implements AzureServiceClientBuilderFactory<T> {

    protected abstract T createBuilderInstance();

    protected abstract void configureClientOptions(T builder);

    protected abstract void configureProxy(T builder);

    protected abstract void configureService(T builder);

    /**
     * TODO: add description
     * @param builder
     */
    public void customizeBuilder(T builder) {

    }

    protected abstract AzureProperties getAzureProperties();

    protected abstract List<AzureCredentialType> getOrderedSupportCredentialTypes();

    protected abstract AzureCredentialManager getAzureCredentialManager(T builder);

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

    public void configureCore(T builder) {
        configureClientOptions(builder);
        configureProxy(builder);
        configureCredentials(resolveAzureCredential(getAzureProperties()), builder);
    }

    protected AzureCredential resolveAzureCredential(AzureProperties azureProperties) {
        List<AzureCredentialType> supportedTypes = getOrderedSupportCredentialTypes();
        List<AzureCredentialResolver> resolvers = getCredentialResolvers()
            .stream()
            .filter(r -> supportedTypes.contains(r.support()))
            .sorted(Comparator.comparingInt(r -> supportedTypes.indexOf(r.support())))
            .collect(Collectors.toList());

        AzureCredential credential = null;
        for (AzureCredentialResolver resolver : resolvers) {
            credential = (AzureCredential) resolver.resolve(azureProperties);
            if (credential != null) {
                break;
            }
        }
        return credential;
    }

    protected List<AzureCredentialResolver> getCredentialResolvers() {
        List<AzureCredentialResolver> resolvers = new ArrayList<>();
        resolvers.add(new AzureTokenCredentialResolver());
        resolvers.add(new AzureKeyCredentialResolver());
        resolvers.add(new AzureNamedKeyCredentialResolver());
        resolvers.add(new AzureSasCredentialResolver());
        return resolvers;
    }

    /**
     * Configure credential and register credential consumer.
     * @param azureCredential
     */
    public void configureCredentials(AzureCredential azureCredential, T builder) {
        getAzureCredentialManager(builder).configureCredential(azureCredential);
    }
}
