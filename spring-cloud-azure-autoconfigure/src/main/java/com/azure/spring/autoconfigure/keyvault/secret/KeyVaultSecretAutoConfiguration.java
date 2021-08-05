package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.security.keyvault.secrets.SecretAsyncClient;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SecretClientBuilder.class)
@ConditionalOnProperty(prefix = "spring.cloud.azure.keyvault.secret", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(KeyVaultSecretProperties.class)
@AutoConfigureAfter({KeyVaultAutoConfiguration.class})
public class KeyVaultSecretAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SecretClient azureKeyVaultSecretClient(SecretClientBuilder builder) {
        return builder.buildClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecretAsyncClient azureKeyVaultSecretAsyncClient(SecretClientBuilder builder) {
        return builder.buildAsyncClient();
    }

    /*@Bean
    @ConditionalOnMissingBean
    public SpringSDKServiceClientBuilderFactory<? extends AzureServiceClientBuilderFactory<SecretClientBuilder>, SecretClientBuilder>
    secretServiceClientBuilderFactory(KeyVaultProperties keyVaultProperties,
                                           KeyVaultCertificateProperties keyVaultCertificateProperties) {
        KeyVaultSecretServiceClientBuilderFactory builder = new KeyVaultSecretServiceClientBuilderFactory(keyVaultProperties, keyVaultCertificateProperties);
        return new SpringSDKServiceClientBuilderFactory<>(builder);
    }

    @Bean
    @ConditionalOnMissingBean
    public SecretClientBuilder secretClientBuilder(SpringSDKServiceClientBuilderFactory<? extends AzureServiceClientBuilderFactory<SecretClientBuilder>, SecretClientBuilder> factory) {
        return factory.create();
    }*/
}
