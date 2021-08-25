package com.azure.spring.autoconfigure;

import com.azure.spring.core.factory.AzureServiceClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public abstract class AzureServiceClientBuilderFactoryTestBase<B, P extends AzureProperties,
                                                                  T extends AzureServiceClientBuilderFactory<B>> {

    protected B configureClientSecretTokenCredential() {
        P properties = createMinimalProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientSecretTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        return createBuilderFactory(properties).build();
    }

    protected B configureClientCertificateTokenCredential() {
        P properties = createMinimalProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientCertificateTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        return createBuilderFactory(properties).build();
    }

    protected abstract T createBuilderFactory(P properties);
    
    protected abstract P createMinimalProperties();
    
    protected TokenCredentialProperties buildClientSecretTokenCredentialProperties() {
        TokenCredentialProperties properties = new TokenCredentialProperties();
        properties.setTenantId("test-tenant");
        properties.setClientId("test-client");
        properties.setClientSecret("test-secret");

        return properties;
    }

    protected TokenCredentialProperties buildClientCertificateTokenCredentialProperties() {
        TokenCredentialProperties properties = new TokenCredentialProperties();
        properties.setTenantId("test-tenant");
        properties.setClientId("test-client");
        properties.setCertificatePath("test-cert-path");
        properties.setCertificatePassword("test-cert-password");

        return properties;
    }
    
    

}
