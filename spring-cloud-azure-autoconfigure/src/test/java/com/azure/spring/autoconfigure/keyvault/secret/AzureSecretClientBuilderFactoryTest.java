package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.identity.ClientCertificateCredential;
import com.azure.identity.ClientSecretCredential;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.spring.autoconfigure.AzureServiceClientBuilderFactoryTestBase;
import com.azure.spring.autoconfigure.core.TestHttpClient;
import com.azure.spring.autoconfigure.core.TestHttpClientProvider;
import com.azure.spring.autoconfigure.core.TestPerCallHttpPipelinePolicy;
import com.azure.spring.autoconfigure.core.TestPerRetryHttpPipelinePolicy;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public class AzureSecretClientBuilderFactoryTest extends AzureServiceClientBuilderFactoryTestBase<SecretClientBuilder, 
                                                                                                  AzureKeyVaultSecretProperties, 
                                                                                                  SecretClientBuilderFactory> {
    private static final String ENDPOINT = "https://test.vault.azure.net/";
    private static SecretClientBuilder builder;

    @BeforeEach
    public void init() {
        builder = mock(SecretClientBuilder.class);
        when(builder.buildAsyncClient()).thenCallRealMethod();
        when(builder.buildClient()).thenCallRealMethod();
    }

    @Test
    public void testMinimalSettings() {
        AzureKeyVaultSecretProperties properties = createMinimalProperties();

        final SecretClientBuilder clientBuilder = new SecretClientBuilderFactoryExt(properties).build();
        final SecretClient client = clientBuilder.buildClient();
    }

    @Test
    public void testClientSecretTokenCredentialConfigured() {
        AzureKeyVaultSecretProperties properties = createMinimalProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientSecretTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final SecretClientBuilder builder = createBuilderFactory(properties).build();
        final SecretClient client = builder.buildClient();

        verify(builder, times(1)).credential(any(ClientSecretCredential.class));
    }

    @Test
    public void testClientCertificateTokenCredentialConfigured() {
        AzureKeyVaultSecretProperties properties = createMinimalProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientCertificateTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final SecretClientBuilder builder = createBuilderFactory(properties).build();
        verify(builder, times(1)).credential(any(ClientCertificateCredential.class));
    }

    @Test
    public void testHttpClientConfigured() {
        AzureKeyVaultSecretProperties properties = createMinimalProperties();

        final SecretClientBuilderFactory builderFactory = createBuilderFactory(properties);

        builderFactory.setHttpClientProvider(new TestHttpClientProvider());

        final SecretClientBuilder builder = builderFactory.build();
        final SecretClient client = builder.buildClient();

        verify(builder).httpClient(any(TestHttpClient.class));
    }

    @Test
    public void testDefaultHttpPipelinePoliciesConfigured() {
        AzureKeyVaultSecretProperties properties = createMinimalProperties();

        final SecretClientBuilderFactory builderFactory = createBuilderFactory(properties);

        builderFactory.addHttpPipelinePolicy(new TestPerCallHttpPipelinePolicy());
        builderFactory.addHttpPipelinePolicy(new TestPerRetryHttpPipelinePolicy());


        final SecretClientBuilder builder = builderFactory.build();
        final SecretClient client = builder.buildClient();

        verify(builder, times(1)).addPolicy(any(TestPerCallHttpPipelinePolicy.class));
        verify(builder, times(1)).addPolicy(any(TestPerRetryHttpPipelinePolicy.class));
    }

    @Test
    public void testCallService() {
        AzureKeyVaultSecretProperties properties = createMinimalProperties();
        properties.setCredential(buildClientSecretTokenCredentialProperties());

        final SecretClientBuilderFactory builderFactory = new SecretClientBuilderFactory(properties);

        final TestPerCallHttpPipelinePolicy perCallPolicy = new TestPerCallHttpPipelinePolicy();
        final TestPerRetryHttpPipelinePolicy perRetryPolicy = new TestPerRetryHttpPipelinePolicy();

        builderFactory.addHttpPipelinePolicy(perCallPolicy);
        builderFactory.addHttpPipelinePolicy(perRetryPolicy);

        final SecretClientBuilder builder = builderFactory.build();
        final SecretClient client = builder.buildClient();

        try {
            final KeyVaultSecret test = client.getSecret("test");
        } catch (Exception ignore) {

        }
        System.out.println(perCallPolicy.getCallTimes());
        System.out.println(perRetryPolicy.getCallTimes());

    }

    @Test
    public void testServicePropertiesConfigured() {
        
        
    }
    
    @Override
    protected SecretClientBuilderFactory createBuilderFactory(AzureKeyVaultSecretProperties properties) {
        return new SecretClientBuilderFactoryExt(properties);
    }

    @Override
    protected AzureKeyVaultSecretProperties createMinimalProperties() {
        AzureKeyVaultSecretProperties properties = new AzureKeyVaultSecretProperties();

        properties.setVaultUrl(ENDPOINT);
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
