package com.azure.spring.autoconfigure.storage.blob;

import com.azure.spring.autoconfigure.core.ServiceClientBuilderCustomizer;
import com.azure.storage.blob.BlobClientBuilder;

public class AzureStorageBlobBuilderCustomizer implements ServiceClientBuilderCustomizer<BlobClientBuilder> {

    @Override
    public void customize(BlobClientBuilder builder) {

    }
}
