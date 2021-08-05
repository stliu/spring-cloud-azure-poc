package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.security.keyvault.certificates.CertificateAsyncClient;
import com.azure.security.keyvault.certificates.CertificateClient;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.spring.autoconfigure.core.SpringSDKServiceClientBuilderFactory;
import com.azure.spring.autoconfigure.core.AzureServiceClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultAutoConfiguration;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(CertificateClientBuilder.class)
@ConditionalOnProperty(prefix = "spring.cloud.azure.keyvault.certificate", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(KeyVaultCertificateProperties.class)
@AutoConfigureAfter({KeyVaultAutoConfiguration.class})
public class KeyVaultCertificateAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CertificateClient azureKeyVaultSecretClient(CertificateClientBuilder builder) {
        return builder.buildClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public CertificateAsyncClient azureKeyVaultSecretAsyncClient(CertificateClientBuilder builder) {
        return builder.buildAsyncClient();
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringSDKServiceClientBuilderFactory<? extends AzureServiceClientBuilder<CertificateClientBuilder>, CertificateClientBuilder>
        certificateServiceClientBuilderFactory(KeyVaultProperties keyVaultProperties,
                                               KeyVaultCertificateProperties keyVaultCertificateProperties) {
        KeyVaultCertificateServiceClientBuilder builder = new KeyVaultCertificateServiceClientBuilder(keyVaultProperties, keyVaultCertificateProperties);
        return new SpringSDKServiceClientBuilderFactory<>(builder);
    }

    @Bean
    @ConditionalOnMissingBean
    public CertificateClientBuilder secretClientBuilder(SpringSDKServiceClientBuilderFactory<? extends AzureServiceClientBuilder<CertificateClientBuilder>, CertificateClientBuilder> factory) {
        return factory.create();
    }
}
