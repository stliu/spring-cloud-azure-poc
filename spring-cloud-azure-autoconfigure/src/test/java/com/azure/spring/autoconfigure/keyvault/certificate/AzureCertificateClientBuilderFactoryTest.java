package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.identity.ClientCertificateCredential;
import com.azure.identity.ClientSecretCredential;
import com.azure.security.keyvault.certificates.CertificateClient;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.security.keyvault.certificates.models.KeyVaultCertificateWithPolicy;
import com.azure.spring.autoconfigure.AzureServiceClientBuilderFactoryTestBase;
import com.azure.spring.autoconfigure.core.TestHttpClient;
import com.azure.spring.autoconfigure.core.TestHttpClientProvider;
import com.azure.spring.autoconfigure.core.TestPerCallHttpPipelinePolicy;
import com.azure.spring.autoconfigure.core.TestPerRetryHttpPipelinePolicy;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public class AzureCertificateClientBuilderFactoryTest extends AzureServiceClientBuilderFactoryTestBase<CertificateClientBuilder, 
                                                                                                          AzureKeyVaultCertificateProperties, 
                                                                                                          CertificateClientBuilderFactory> {
    private static final String ENDPOINT = "https://test.vault.azure.net/";

    @Test
    public void testMinimalSettings() {
        AzureKeyVaultCertificateProperties properties = createMinimalServiceProperties();
        properties.setCredential(buildClientCertificateTokenCredentialProperties());

        final CertificateClientBuilder clientBuilder = new CertificateClientBuilderFactory(properties).build();
        clientBuilder.buildClient();
    }

    @Test
    public void testClientSecretTokenCredentialConfigured() {
        AzureKeyVaultCertificateProperties properties = createMinimalServiceProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientSecretTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final CertificateClientBuilder builder = new CertificateClientBuilderFactoryExt(properties).build();

        verify(builder, times(1)).credential(any(ClientSecretCredential.class));
    }

    @Test
    public void testClientCertificateTokenCredentialConfigured() {
        AzureKeyVaultCertificateProperties properties = createMinimalServiceProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientCertificateTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final CertificateClientBuilder builder = new CertificateClientBuilderFactoryExt(properties).build();
        verify(builder, times(1)).credential(any(ClientCertificateCredential.class));
    }

    @Test
    public void testHttpClientConfigured() {
        AzureKeyVaultCertificateProperties properties = createMinimalServiceProperties();

        final CertificateClientBuilderFactory builderFactory = new CertificateClientBuilderFactoryExt(properties);

        builderFactory.setHttpClientProvider(new TestHttpClientProvider());

        final CertificateClientBuilder builder = builderFactory.build();

        verify(builder).httpClient(any(TestHttpClient.class));
    }

    @Test
    public void testDefaultHttpPipelinePoliciesConfigured() {
        AzureKeyVaultCertificateProperties properties = createMinimalServiceProperties();

        final CertificateClientBuilderFactory builderFactory = new CertificateClientBuilderFactoryExt(properties);

        builderFactory.addHttpPipelinePolicy(new TestPerCallHttpPipelinePolicy());
        builderFactory.addHttpPipelinePolicy(new TestPerRetryHttpPipelinePolicy());


        final CertificateClientBuilder builder = builderFactory.build();

        verify(builder, times(1)).addPolicy(any(TestPerCallHttpPipelinePolicy.class));
        verify(builder, times(1)).addPolicy(any(TestPerRetryHttpPipelinePolicy.class));
    }

    @Test
    public void testCallService() {
        AzureKeyVaultCertificateProperties properties = createMinimalServiceProperties();
        properties.setCredential(buildClientSecretTokenCredentialProperties());

        final CertificateClientBuilderFactory builderFactory = new CertificateClientBuilderFactory(properties);

        final TestPerCallHttpPipelinePolicy perCallPolicy = new TestPerCallHttpPipelinePolicy();
        final TestPerRetryHttpPipelinePolicy perRetryPolicy = new TestPerRetryHttpPipelinePolicy();

        builderFactory.addHttpPipelinePolicy(perCallPolicy);
        builderFactory.addHttpPipelinePolicy(perRetryPolicy);

        final CertificateClientBuilder builder = builderFactory.build();
        final CertificateClient client = builder.buildClient();

        try {
            final KeyVaultCertificateWithPolicy test = client.getCertificate("test");
        } catch (Exception ignore) {

        }
        System.out.println(perCallPolicy.getCallTimes());
        System.out.println(perRetryPolicy.getCallTimes());

    }

    @Test
    public void testServicePropertiesConfigured() {
        
        
    }

    @Override
    protected AzureKeyVaultCertificateProperties createMinimalServiceProperties() {
        AzureKeyVaultCertificateProperties properties = new AzureKeyVaultCertificateProperties();

        properties.setVaultUrl(ENDPOINT);
        return properties;
    }

    static class CertificateClientBuilderFactoryExt extends CertificateClientBuilderFactory {

        public CertificateClientBuilderFactoryExt(AzureKeyVaultCertificateProperties secretProperties) {
            super(secretProperties);
        }

        @Override
        public CertificateClientBuilder createBuilderInstance() {
            return mock(CertificateClientBuilder.class);
        }
    }


}
