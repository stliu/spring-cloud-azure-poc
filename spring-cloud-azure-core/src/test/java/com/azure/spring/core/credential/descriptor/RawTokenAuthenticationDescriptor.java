package com.azure.spring.core.credential.descriptor;

import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.provider.AzureRawTokenCredentialProvider;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureRawTokenCredentialResolver;

import java.util.function.Consumer;

import static com.azure.spring.core.credential.provider.AzureRawTokenCredentialProvider.RAW_TOKEN_CREDENTIAL;

public class RawTokenAuthenticationDescriptor implements AuthenticationDescriptor<AzureRawTokenCredentialProvider> {

    private final Consumer<AzureRawTokenCredentialProvider> consumer;

    public RawTokenAuthenticationDescriptor(Consumer<AzureRawTokenCredentialProvider> consumer) {
        this.consumer = consumer;
    }

    @Override
    public AzureCredentialType azureCredentialType() {
        return RAW_TOKEN_CREDENTIAL;
    }

    @Override
    public AzureCredentialResolver<AzureRawTokenCredentialProvider> azureCredentialResolver() {
        return new AzureRawTokenCredentialResolver();
    }

    @Override
    public Consumer<AzureRawTokenCredentialProvider> consumer() {
        return consumer;
    }
}