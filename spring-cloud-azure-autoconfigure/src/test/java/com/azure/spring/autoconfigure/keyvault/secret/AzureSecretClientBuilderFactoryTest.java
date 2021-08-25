package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.identity.ClientCertificateCredential;
import com.azure.identity.ClientSecretCredential;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.spring.autoconfigure.AzureHttpClientBuilderFactoryTestBase;
import com.azure.spring.autoconfigure.core.TestHttpClient;
import com.azure.spring.autoconfigure.core.TestPerCallHttpPipelinePolicy;
import com.azure.spring.autoconfigure.core.TestPerRetryHttpPipelinePolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public class AzureSecretClientBuilderFactoryTest extends AzureHttpClientBuilderFactoryTestBase<SecretClientBuilder, 
                                                                                                  AzureKeyVaultSecretProperties, 
                                                                                                  SecretClientBuilderFactory> {

    private static SecretClientBuilder builder;

    @BeforeEach
    public void init() {
        builder = mock(SecretClientBuilder.class);
    }

    @Test
    public void testMinimalSettings() {
        AzureKeyVaultSecretProperties properties = createMinimalProperties();

        final SecretClientBuilder clientBuilder = new SecretClientBuilderFactoryExt(properties).build();
        final SecretClient client = clientBuilder.buildClient();
    }

    @Test
    public void testClientSecretTokenCredentialConfigured() {
        final SecretClientBuilder builder = configureClientSecretTokenCredential();
        final SecretClient client = builder.buildClient();

        verify(builder, times(1)).credential(any(ClientSecretCredential.class));
    }

    @Test
    public void testClientCertificateTokenCredentialConfigured() {
        final SecretClientBuilder builder = configureClientCertificateTokenCredential();
        verify(builder, times(1)).credential(any(ClientCertificateCredential.class));
    }

    @Test
    public void testHttpClientConfigured() {
        final SecretClientBuilder builder = configureHttpClient();
        final SecretClient client = builder.buildClient();

        verify(builder).httpClient(any(TestHttpClient.class));
    }

    @Test
    public void testDefaultHttpPipelinePoliciesConfigured() {
        final SecretClientBuilder builder = configureDefaultHttpPipelinePolicies();
        final SecretClient client = builder.buildClient();

        verify(builder, times(1)).addPolicy(any(TestPerCallHttpPipelinePolicy.class));
        verify(builder, times(1)).addPolicy(any(TestPerRetryHttpPipelinePolicy.class));
    }
    
    @Override
    protected SecretClientBuilderFactory createBuilderFactory(AzureKeyVaultSecretProperties properties) {
        return new SecretClientBuilderFactoryExt(properties);
    }

    @Override
    protected AzureKeyVaultSecretProperties createMinimalProperties() {
        AzureKeyVaultSecretProperties properties = new AzureKeyVaultSecretProperties();
        return properties;
    }

    static class SecretClientBuilderFactoryExt extends SecretClientBuilderFactory {

        public SecretClientBuilderFactoryExt(AzureKeyVaultSecretProperties secretProperties) {
            super(secretProperties);
        }

        @Override
        public SecretClientBuilder createBuilderInstance() {
            return builder;
        }
    }


}
