// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.autoconfigure.cosmos;

import com.azure.core.credential.TokenCredential;
import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.azure.spring.core.AzureTokenCredentialResolver.DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME;

/**
 * Auto Configure Cosmos properties and connection policy.
 */
@Configuration
@ConditionalOnClass({ CosmosAsyncClient.class, CosmosTemplate.class })
@ConditionalOnResource(resources = "classpath:cosmos.enable.config")
@EnableConfigurationProperties(CosmosProperties.class)
//@AutoConfigureAfter(AzureDefaultTokenCredentialAutoConfiguration.class)
public class CosmosAutoConfiguration extends AbstractCosmosConfiguration {
    private final CosmosProperties properties;
    private static final String COSMOS_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME = "cosmosChainedTokenCredential";
    private static final String COSMOS_AZURE_KEY_CREDENTIAL_BEAN_NAME = "cosmosAzureKeyCredential";

    public CosmosAutoConfiguration(CosmosProperties properties) {
        this.properties = properties;
    }

    @Override
    protected String getDatabaseName() {
        return properties.getDatabase();
    }

    @Override
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
                           .enableQueryMetrics(properties.isPopulateQueryMetrics())
                           .responseDiagnosticsProcessor(properties.getResponseDiagnosticsProcessor())
                           .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CosmosServiceClientBuilderFactory factory(@Qualifier(DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME) TokenCredential tokenCredential,
                                                     CosmosProperties cosmosProperties) {
        return new CosmosServiceClientBuilderFactory(tokenCredential, cosmosProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public CosmosClientBuilder cosmosClientBuilder(CosmosServiceClientBuilderFactory factory) {
        return factory.build();
    }
}
