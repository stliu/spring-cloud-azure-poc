package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.security.keyvault.certificates.CertificateAsyncClient;
import com.azure.security.keyvault.certificates.CertificateClient;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
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

}
