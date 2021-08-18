package com.azure.spring.core.credential.descriptor;

import com.azure.core.credential.AzureNamedKeyCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.provider.AzureNamedKeyCredentialProvider;
import com.azure.spring.core.credential.resolver.AzureNamedKeyCredentialResolver;

import java.util.function.Consumer;

public class NamedKeyAuthenticationDescriptor implements AuthenticationDescriptor<AzureNamedKeyCredentialProvider, AzureNamedKeyCredential> {

    private final Consumer<AzureNamedKeyCredentialProvider> consumer;

    public NamedKeyAuthenticationDescriptor(Consumer<AzureNamedKeyCredentialProvider> consumer) {
        this.consumer = consumer;
    }

    @Override
    public AzureCredentialType azureCredentialType() {
        return AzureCredentialType.KEY_CREDENTIAL;
    }

    @Override
    public AzureNamedKeyCredentialResolver azureCredentialResolver() {
        return new AzureNamedKeyCredentialResolver();
    }

    @Override
    public Consumer<AzureNamedKeyCredentialProvider> consumer() {
        return consumer;
    }
}
