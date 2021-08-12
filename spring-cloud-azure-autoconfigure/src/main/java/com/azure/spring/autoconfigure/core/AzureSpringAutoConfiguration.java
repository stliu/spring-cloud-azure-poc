package com.azure.spring.autoconfigure.core;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.amqp.AzureSpringAmqpConfigurationContext;
import com.azure.spring.core.amqp.DefaultAzureSpringAmqpConfigurationContext;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.http.DefaultAzureSpringHttpConfigurationContext;
import com.azure.spring.core.http.AzureSpringHttpConfigurationContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//import static com.azure.spring.core.credential.AzureTokenCredentialResolver.DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME;

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
