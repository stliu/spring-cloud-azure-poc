package com.azure.spring.autoconfigure.demo;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.ChainedTokenCredential;
import com.azure.identity.ChainedTokenCredentialBuilder;
import com.azure.spring.autoconfigure.storage.blob.AzureStorageBlobProperties;
import com.azure.spring.autoconfigure.storage.blob.AzureStorageBlobServiceClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.http.DefaultAzureSpringHttpConfigurationContext;
import com.azure.spring.core.http.HttpClientSupplier;
import com.azure.spring.core.http.HttpClientSupplierImpl;
import com.azure.spring.core.http.HttpPipelinePoliciesSupplier;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import com.azure.spring.core.properties.http.HttpProperties;
import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;


public class DemoStorageBlob {

    public static void main(String[] args) {
        AzureStorageBlobProperties blobProperties = new AzureStorageBlobProperties();
        TokenCredential tokenCredential = new ChainedTokenCredentialBuilder().build();
        AzureProperties rootProperties = new AzureProperties();
        HttpProperties rootHttpProperties = new HttpProperties();
        Environment env = new StandardEnvironment();
        AzureSpringHttpConfigurationContext httpConfigurationContext =
            new DefaultAzureSpringHttpConfigurationContext(tokenCredential, rootProperties, env);
        AzureStorageBlobServiceClientBuilderFactory builderFactory =
            new AzureStorageBlobServiceClientBuilderFactory(httpConfigurationContext, blobProperties, null);

        //
//        List<AzureServiceFeature> azureServiceFeatures = builderFactory.supportFeatures();
//        List<HttpPipelinePolicy> httpPipelinePolicies = builderFactory.getHttpPipelinePolicies();
        HttpPipelinePoliciesSupplier httpPipelinePoliciesSupplier = builderFactory.getHttpPipelinePolicySupplier();

        HttpClientSupplier httpClientSupplier =
            new HttpClientSupplierImpl(rootHttpProperties, blobProperties.getHttp());

        // feature, -> resolve -> policies
        // client id & cert > policy -> pipeline
        //

//        HttpPipeline hp = new HttpPipelineBuilder()
//            .httpClient(httpClientSupplier.get())
//            .policies(httpPipelinePoliciesSupplier.get().toArray(new HttpPipelinePolicy[0]))
//            .build();
//        builderFactory.setPipeline(hp);
        BlobClientBuilder build = builderFactory.build();

    }
}
