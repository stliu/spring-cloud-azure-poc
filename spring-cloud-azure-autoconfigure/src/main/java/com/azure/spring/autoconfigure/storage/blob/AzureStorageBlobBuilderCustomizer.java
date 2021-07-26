package com.azure.spring.autoconfigure.storage.blob;

import com.azure.spring.autoconfigure.core.ServiceClientBuilderCustomizer;
import com.azure.storage.blob.BlobClientBuilder;

public interface AzureStorageBlobBuilderCustomizer extends ServiceClientBuilderCustomizer<BlobClientBuilder> {

    @Override
    default void customize(BlobClientBuilder builder) {
    }
}
