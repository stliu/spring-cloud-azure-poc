package com.azure.spring.core;

import com.azure.identity.implementation.IdentityClient;
import com.azure.identity.implementation.IdentityClientBuilder;
import com.azure.spring.core.factory.AzureIdentityClientBuilderFactory;
import com.azure.spring.core.properties.IdentityClientProperties;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;
import com.azure.spring.core.properties.retry.RetryProperties;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AzureIdentityClientBuilderFactoryTest {

    private static IdentityClientBuilder builder;

    @Before
    public void init() {
        builder = mock(IdentityClientBuilder.class);
    }

    @Test
    public void testNoPropertiesConfigured() {
        IdentityClientProperties properties = new IdentityClientProperties();
        new AzureIdentityClientBuilderFactory(properties).build();
    }

    @Test
    public void testClientSecretTokenCredentialConfigured() {
        IdentityClientProperties properties = new IdentityClientProperties();
        TokenCredentialProperties tokenCredentialProperties = new TokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);
        tokenCredentialProperties.setTenantId("test-tenant");
        tokenCredentialProperties.setClientId("test-id");
        tokenCredentialProperties.setClientSecret("test-secret");
        final IdentityClientBuilder identityClientBuilder = new AzureIdentityClientBuilderFactoryExt(properties).build();
        final IdentityClient identityClient = identityClientBuilder.build();
        verify(builder, times(1)).tenantId("test-tenant");
        verify(builder, times(1)).clientId("test-id");
        verify(builder, times(1)).clientSecret("test-secret");
    }

    @Test
    public void testClientCertificateTokenCredentialConfigured() {
        IdentityClientProperties properties = new IdentityClientProperties();
        TokenCredentialProperties tokenCredentialProperties = new TokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);
        tokenCredentialProperties.setTenantId("test-tenant");
        tokenCredentialProperties.setCertificatePath("test-cert-path");
        tokenCredentialProperties.setCertificatePassword("test-cert-password");
        final IdentityClientBuilder identityClientBuilder = new AzureIdentityClientBuilderFactoryExt(properties).build();
        final IdentityClient identityClient = identityClientBuilder.build();
        verify(builder, times(1)).tenantId("test-tenant");
        verify(builder, times(1)).certificatePath("test-cert-path");
        verify(builder, times(1)).certificatePassword("test-cert-password");
    }

    @Test
    public void testRetryConfigured() {
        IdentityClientProperties properties = new IdentityClientProperties();
        RetryProperties retryProperties = new RetryProperties();
        properties.setRetry(retryProperties);
        retryProperties.setMaxAttempts(5);

        new AzureIdentityClientBuilderFactoryExt(properties).build().build();
    }

    static class AzureIdentityClientBuilderFactoryExt extends AzureIdentityClientBuilderFactory {

        public AzureIdentityClientBuilderFactoryExt(IdentityClientProperties properties) {
            super(properties);
        }

        @Override
        protected IdentityClientBuilder createBuilderInstance() {
            return builder;
        }
    }


}
