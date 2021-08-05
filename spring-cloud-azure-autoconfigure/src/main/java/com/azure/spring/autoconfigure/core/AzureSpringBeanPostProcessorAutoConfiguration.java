package com.azure.spring.autoconfigure.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Azure spring bean post processor auto-configuration.
 */
@Configuration(proxyBeanMethods = false)
public class AzureSpringBeanPostProcessorAutoConfiguration {

    @Bean
    public HttpPipelinePostProcessor httpPipelinePostProcessor() {
        return new HttpPipelinePostProcessor();
    }

    @Bean
    public SpringKeyCredentialPostProcessor springKeyCredentialPostProcessor() {
        return new SpringKeyCredentialPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenCredentialSpringPostProcessor tokenCredentialPostProcessor() {
        return new TokenCredentialSpringPostProcessor();
    }

}
