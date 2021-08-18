package com.azure.spring.autoconfigure.demo;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.ChainedTokenCredentialBuilder;
import com.azure.spring.autoconfigure.storage.blob.AzureStorageBlobProperties;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.context.DefaultAzureSpringHttpConfigurationContext;
import com.azure.spring.core.context.AzureSpringHttpConfigurationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;


public class DemoStorageBlob {

    public static void main(String[] args) {
        AzureStorageBlobProperties blobProperties = new AzureStorageBlobProperties();
        TokenCredential tokenCredential = new ChainedTokenCredentialBuilder().build();
        AzureProperties rootProperties = new AzureProperties();
        Environment env = new StandardEnvironment();
        AzureSpringHttpConfigurationContext httpConfigurationContext =
            new DefaultAzureSpringHttpConfigurationContext(tokenCredential, rootProperties, env);
//        AzureBlobClientBuilderFactory builderFactory =
//            new AzureBlobClientBuilderFactory(httpConfigurationContext, blobProperties, null);

        //
//        List<AzureServiceFeature> azureServiceFeatures = builderFactory.supportFeatures();
//        List<HttpPipelinePolicy> httpPipelinePolicies = builderFactory.getHttpPipelinePolicies();
//        HttpPipelinePoliciesSupplier httpPipelinePoliciesSupplier = builderFactory.getHttpPipelinePolicySupplier();
//
//        HttpClientSupplier httpClientSupplier =
//            new HttpClientSupplierImpl(rootHttpProperties, blobProperties.getHttp());

        // feature, -> resolve -> policies
        // client id & cert > policy -> pipeline
        //

//        HttpPipeline hp = new HttpPipelineBuilder()
//            .httpClient(httpClientSupplier.get())
//            .policies(httpPipelinePoliciesSupplier.get().toArray(new HttpPipelinePolicy[0]))
//            .build();
//        builderFactory.setPipeline(hp);
//        BlobClientBuilder build = builderFactory.build();

    }
}
