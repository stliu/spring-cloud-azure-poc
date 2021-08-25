package com.azure.spring.autoconfigure.storage.blob;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpClientProvider;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.util.Context;
import com.azure.identity.ClientCertificateCredential;
import com.azure.identity.ClientSecretCredential;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public class AzureBlobClientBuilderFactoryTest {

    private static BlobClientBuilder builder;
    private static final String ENDPOINT = "https://abc.blob.core.windows.net/";
    private static final String BLOB_NAME = "test-blob";

    @BeforeEach
    public void init() {
        builder = mock(BlobClientBuilder.class);
    }

    @Test
    public void testMinimalSettings() {
        AzureStorageBlobProperties properties = new AzureStorageBlobProperties();
        properties.setEndpoint(ENDPOINT);
        properties.setBlobName(BLOB_NAME);

        final BlobClientBuilder blobClientBuilder = new AzureBlobClientBuilderFactoryExt(properties).build();
        final BlobClient blobClient = blobClientBuilder.buildClient();
    }

    @Test
    public void testClientSecretTokenCredentialConfigured() {
        AzureStorageBlobProperties properties = new AzureStorageBlobProperties();
        properties.setEndpoint(ENDPOINT);
        properties.setBlobName(BLOB_NAME);

        TokenCredentialProperties tokenCredentialProperties = new TokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);
        tokenCredentialProperties.setTenantId("test-tenant");
        tokenCredentialProperties.setClientId("test-client");
        tokenCredentialProperties.setClientSecret("test-secret");

        final BlobClientBuilder blobClientBuilder = new AzureBlobClientBuilderFactoryExt(properties).build();
        final BlobClient blobClient = blobClientBuilder.buildClient();

        verify(builder, times(1)).credential(any(ClientSecretCredential.class));
    }

    @Test
    public void testClientCertificateTokenCredentialConfigured() {
        AzureStorageBlobProperties properties = new AzureStorageBlobProperties();
        properties.setEndpoint(ENDPOINT);
        properties.setBlobName(BLOB_NAME);

        TokenCredentialProperties tokenCredentialProperties = new TokenCredentialProperties();
        properties.setCredential(tokenCredentialProperties);
        tokenCredentialProperties.setTenantId("test-tenant");
        tokenCredentialProperties.setClientId("test-client");
        tokenCredentialProperties.setCertificatePath("test-cert-path");
        tokenCredentialProperties.setCertificatePassword("test-cert-password");

        final BlobClientBuilder blobClientBuilder = new AzureBlobClientBuilderFactoryExt(properties).build();
        final BlobClient blobClient = blobClientBuilder.buildClient();

        verify(builder, times(1)).credential(any(ClientCertificateCredential.class));
    }

    @Test
    public void testSetHttpClient() {
        AzureStorageBlobProperties properties = new AzureStorageBlobProperties();
        properties.setEndpoint(ENDPOINT);
        properties.setBlobName(BLOB_NAME);

        final AzureBlobClientBuilderFactoryExt builderFactory = new AzureBlobClientBuilderFactoryExt(properties);

        builderFactory.setHttpClientProvider(new TestHttpClientProvider());
        final BlobClientBuilder blobClientBuilder = builderFactory.build();
        final BlobClient blobClient = blobClientBuilder.buildClient();

        verify(builder, times(1)).httpClient(any(TestHttpClient.class));
    }

    static class TestHttpClientProvider implements HttpClientProvider {

        @Override
        public HttpClient createInstance() {
            return new TestHttpClient();
        }
    }

    static class TestHttpClient implements HttpClient {

        @Override
        public Mono<HttpResponse> send(HttpRequest request) {
            return null;
        }

        @Override
        public Mono<HttpResponse> send(HttpRequest request, Context context) {
            return HttpClient.super.send(request, context);
        }
    }

    static class AzureBlobClientBuilderFactoryExt extends AzureBlobClientBuilderFactory {

        public AzureBlobClientBuilderFactoryExt(AzureStorageBlobProperties blobProperties) {
            super(blobProperties);
        }

        @Override
        public BlobClientBuilder createBuilderInstance() {
            return builder;
        }
    }

}
