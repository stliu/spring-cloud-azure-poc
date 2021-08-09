package com.azure.spring.autoconfigure.core;

import com.azure.spring.core.AzureProperties;
import com.azure.spring.core.http.DefaultAzureSpringHttpConfigurationContext;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AzureProperties.class)
@AutoConfigureAfter
public class AzureSpringAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AzureSpringHttpConfigurationContext defaultAzureSpringHttpContext(AzureProperties azureProperties,
                                                                             Environment environment) {
        return new DefaultAzureSpringHttpConfigurationContext(azureProperties, environment);
    }
}
