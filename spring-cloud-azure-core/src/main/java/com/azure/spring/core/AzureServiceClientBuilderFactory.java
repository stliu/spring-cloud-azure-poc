package com.azure.spring.core;

import com.azure.core.credential.TokenCredential;
import com.azure.core.util.ClientOptions;
import com.azure.core.util.Configuration;
import com.azure.core.util.Header;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.ClientOptionsProperties;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Azure SDK service client builder factory of all modules.
 * @param <T>
 */
public interface AzureServiceClientBuilderFactory<T> {

    String CLIENT_OPTIONS_METHOD_NAME = "clientOptions";
    String CONFIGURATION_METHOD_NAME = "configuration";
    String TOKEN_CREDENTIAL_METHOD_NAME = "credential";

    AzureSpringConfigurationContext getConfigurationContext();

    T build();

    T builder();

//    List<AzureServiceFeature> supportFeatures();

    default AzureServiceClientBuilderFactory configuration(Configuration configuration) {
        invoke(defaultConfigurationMethodName(), new Class[] {Configuration.class}, configuration);
        return this;
    }

    default AzureServiceClientBuilderFactory clientOptions(ClientOptions clientOptions) {
        invoke(defaultClientOptionsMethodName(), new Class[] {ClientOptions.class}, clientOptions);
        return this;
    }

    default AzureServiceClientBuilderFactory credential(TokenCredential credential) {
        invoke(defaultTokenCredentialMethodName(), new Class[] { TokenCredential.class}, credential);
        return this;
    }

    default void invoke(String methodName, Class<?> [] parameterTypes , Object... args) {
        try {
            Method clientOptionsMethod = builder().getClass().getMethod(methodName, parameterTypes);
            clientOptionsMethod.invoke(builder(), args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    default String defaultClientOptionsMethodName() {
        return CLIENT_OPTIONS_METHOD_NAME;
    }

    default String defaultConfigurationMethodName() {
        return CONFIGURATION_METHOD_NAME;
    }

    default String defaultTokenCredentialMethodName() {
        return TOKEN_CREDENTIAL_METHOD_NAME;
    }

    default ClientOptions getClientOptions(ClientOptionsProperties client) {
        ClientOptions clientOptions = new ClientOptions();
        Optional.ofNullable(client.getApplicationId()).ifPresent(clientOptions::setApplicationId);
        List<Header> headers = new ArrayList<>();
        Optional.ofNullable(client.getHeaders()).ifPresent(headerProperties -> {
            headerProperties.forEach(headerProp -> {
                headers.add(new Header(headerProp.getName(), headerProp.getValues()));
            });
        });
        clientOptions.setHeaders(headers);
        return clientOptions;
    }

    default TokenCredential getTokenCredential(AzureProperties azureProperties) {
        return new AzureTokenCredentialResolver().resolve(getDefaultTokenCredential(), azureProperties);
    }

    default Configuration getDefaultConfiguration() {
        return getConfigurationContext().getAzureDefaultConfiguration();
    }
    default TokenCredential getDefaultTokenCredential() {
        return getConfigurationContext().getTokenCredential();
    }
}



