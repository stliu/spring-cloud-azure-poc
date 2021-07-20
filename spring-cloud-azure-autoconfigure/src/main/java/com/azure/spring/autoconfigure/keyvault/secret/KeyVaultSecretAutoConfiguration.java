package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.security.keyvault.secrets.SecretAsyncClient;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SecretClientBuilder.class)
@ConditionalOnProperty(prefix = "spring.cloud.azure.keyvault.secret", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(KeyVaultSecretProperties.class)
@AutoConfigureAfter
public class KeyVaultSecretAutoConfiguration {

    @Bean
    public SecretClient azureKeyVaultSecretClient(SecretClientBuilder builder) {
        return builder.buildClient();
    }
    @Bean
    public SecretAsyncClient azureKeyVaultSecretAsyncClient(SecretClientBuilder builder) {
        return builder.buildAsyncClient();
    }
    @Bean
    public SecretClientBuilder secretClientBuilder(KeyVaultSecretProperties keyVaultSecretProperties,
        KeyVaultSecretBuilderCustomizer configurer) {
        SecretClientBuilder builder = new SecretClientBuilder();
        builder.vaultUrl(keyVaultSecretProperties.getEndpoint());
        if (keyVaultSecretProperties.getServiceVersion() != null) {
            builder.serviceVersion(keyVaultSecretProperties.getServiceVersion());
        }
        configurer.customize(builder);
        return builder;
    }

}
