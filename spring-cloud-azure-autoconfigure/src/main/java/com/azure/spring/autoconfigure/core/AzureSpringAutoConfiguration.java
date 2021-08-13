package com.azure.spring.autoconfigure.core;

import com.azure.spring.core.properties.AzureProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//import static com.azure.spring.core.credential.resolver.AzureTokenCredentialResolver.DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AzureProperties.class)
@AutoConfigureAfter
public class AzureSpringAutoConfiguration {

//    @Bean
//    @ConditionalOnMissingBean
//    public AzureSpringHttpConfigurationContext azureSpringHttpConfigurationContext(
//        @Qualifier(DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME) TokenCredential tokenCredential,
//        AzureProperties azureProperties,
//        Environment environment) {
//        return new DefaultAzureSpringHttpConfigurationContext(tokenCredential, azureProperties, environment);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public AzureSpringAmqpConfigurationContext azureSpringAmqpConfigurationContext(
//        @Qualifier(DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME) TokenCredential tokenCredential,
//        AzureProperties azureProperties,
//        Environment environment) {
//        return new DefaultAzureSpringAmqpConfigurationContext(tokenCredential, azureProperties, environment);
//    }
}
