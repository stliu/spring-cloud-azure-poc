package com.azure.spring.autoconfigure.storage.blob;

import com.azure.identity.ClientCertificateCredential;
import com.azure.identity.ClientSecretCredential;
import com.azure.spring.autoconfigure.AzureServiceClientBuilderFactoryTestBase;
import com.azure.spring.autoconfigure.core.TestHttpClient;
import com.azure.spring.autoconfigure.core.TestHttpClientProvider;
import com.azure.spring.autoconfigure.core.TestPerCallHttpPipelinePolicy;
import com.azure.spring.autoconfigure.core.TestPerRetryHttpPipelinePolicy;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public class AzureBlobClientBuilderFactoryTest extends AzureServiceClientBuilderFactoryTestBase<BlobClientBuilder,
                                                                                                AzureStorageBlobProperties,
                                                                                                BlobClientBuilderFactory> {

    private static final String ENDPOINT = "https://abc.blob.core.windows.net/";
    private static final String BLOB_NAME = "test-blob";

    @Test
    public void testMinimalSettings() {
        AzureStorageBlobProperties properties = createMinimalServiceProperties();

        final BlobClientBuilder clientBuilder = new BlobClientBuilderFactory(properties).build();
        final BlobClient client = clientBuilder.buildClient();
    }

    @Test
    public void testClientSecretTokenCredentialConfigured() {
        AzureStorageBlobProperties properties = createMinimalServiceProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientSecretTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final BlobClientBuilder builder = new BlobClientBuilderFactoryExt(properties).build();
        final BlobClient client = builder.buildClient();

        verify(builder, times(1)).credential(any(ClientSecretCredential.class));
    }

    @Test
    public void testClientCertificateTokenCredentialConfigured() {
        AzureStorageBlobProperties properties = createMinimalServiceProperties();

        TokenCredentialProperties tokenCredentialProperties = buildClientCertificateTokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);

        final BlobClientBuilder builder = new BlobClientBuilderFactoryExt(properties).build();
        verify(builder, times(1)).credential(any(ClientCertificateCredential.class));
    }

    @Test
    public void testHttpClientConfigured() {
        AzureStorageBlobProperties properties = createMinimalServiceProperties();

        final BlobClientBuilderFactory builderFactory = new BlobClientBuilderFactoryExt(properties);

        builderFactory.setHttpClientProvider(new TestHttpClientProvider());

        final BlobClientBuilder builder = builderFactory.build();
        final BlobClient client = builder.buildClient();

        verify(builder).httpClient(any(TestHttpClient.class));
    }

    @Test
    public void testDefaultHttpPipelinePoliciesConfigured() {
        AzureStorageBlobProperties properties = createMinimalServiceProperties();

        final BlobClientBuilderFactory builderFactory = new BlobClientBuilderFactoryExt(properties);

        builderFactory.addHttpPipelinePolicy(new TestPerCallHttpPipelinePolicy());
        builderFactory.addHttpPipelinePolicy(new TestPerRetryHttpPipelinePolicy());


        final BlobClientBuilder builder = builderFactory.build();
        final BlobClient client = builder.buildClient();

        verify(builder, times(1)).addPolicy(any(TestPerCallHttpPipelinePolicy.class));
        verify(builder, times(1)).addPolicy(any(TestPerRetryHttpPipelinePolicy.class));
    }

    @Override
    protected AzureStorageBlobProperties createMinimalServiceProperties() {
        AzureStorageBlobProperties properties = new AzureStorageBlobProperties();
        properties.setEndpoint(ENDPOINT);
        properties.setBlobName(BLOB_NAME);
        return properties;
    }

    static class BlobClientBuilderFactoryExt extends BlobClientBuilderFactory {

        public BlobClientBuilderFactoryExt(AzureStorageBlobProperties blobProperties) {
            super(blobProperties);
        }

        @Override
        public BlobClientBuilder createBuilderInstance() {
            return mock(BlobClientBuilder.class);
        }
    }

}
