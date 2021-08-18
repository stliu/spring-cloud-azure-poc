package com.azure.spring.core.credential.descriptor;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.provider.AzureTokenCredentialProvider;
import com.azure.spring.core.credential.resolver.AzureCredentialResolver;
import com.azure.spring.core.credential.resolver.AzureTokenCredentialResolver;

import java.util.function.Consumer;

public class TokenAuthenticationDescriptor implements AuthenticationDescriptor<AzureTokenCredentialProvider, TokenCredential> {

    private final Consumer<AzureTokenCredentialProvider> consumer;

    public TokenAuthenticationDescriptor(Consumer<AzureTokenCredentialProvider> consumer) {
        this.consumer = consumer;
    }

    @Override
    public AzureCredentialType azureCredentialType() {
        return AzureCredentialType.TOKEN_CREDENTIAL;
    }

    @Override
    public AzureCredentialResolver azureCredentialResolver() {
        return new AzureTokenCredentialResolver();
    }

    @Override
    public Consumer<AzureTokenCredentialProvider> consumer() {
        return consumer;
    }
}
