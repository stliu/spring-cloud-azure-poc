// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.autoconfigure.cosmos;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Auto Configure Cosmos properties and connection policy.
 */
@Configuration
@ConditionalOnClass({ CosmosAsyncClient.class, CosmosTemplate.class })
@ConditionalOnResource(resources = "classpath:cosmos.enable.config")
@EnableConfigurationProperties(CosmosProperties.class)
public class CosmosAutoConfiguration {



}
