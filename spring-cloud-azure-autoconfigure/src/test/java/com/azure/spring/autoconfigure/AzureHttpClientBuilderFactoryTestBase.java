package com.azure.spring.autoconfigure;

import com.azure.spring.autoconfigure.core.TestHttpClientProvider;
import com.azure.spring.autoconfigure.core.TestPerCallHttpPipelinePolicy;
import com.azure.spring.autoconfigure.core.TestPerRetryHttpPipelinePolicy;
import com.azure.spring.core.factory.AbstractAzureHttpClientBuilderFactory;
import com.azure.spring.core.properties.AzureProperties;

/**
 * @author Xiaolu Dai, 2021/8/25.
 */
public abstract class AzureHttpClientBuilderFactoryTestBase<B, P extends AzureProperties,
                                                               T extends AbstractAzureHttpClientBuilderFactory<B>>
    extends AzureServiceClientBuilderFactoryTestBase<B, P, T> {

    protected B configureHttpClient() {
        P properties = createMinimalProperties();

        final T builderFactory = createBuilderFactory(properties);

        builderFactory.setHttpClientProvider(new TestHttpClientProvider());

        return builderFactory.build();

    }

    protected B configureDefaultHttpPipelinePolicies() {
        P properties = createMinimalProperties();

        final T builderFactory = createBuilderFactory(properties);

        builderFactory.addHttpPipelinePolicy(new TestPerCallHttpPipelinePolicy());
        builderFactory.addHttpPipelinePolicy(new TestPerRetryHttpPipelinePolicy());

        return builderFactory.build();
    }

}
