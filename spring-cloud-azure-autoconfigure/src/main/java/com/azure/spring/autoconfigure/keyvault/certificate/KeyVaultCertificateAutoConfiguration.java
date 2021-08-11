package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.core.credential.TokenCredential;
import com.azure.security.keyvault.certificates.CertificateAsyncClient;
import com.azure.security.keyvault.certificates.CertificateClient;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultAutoConfiguration;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.core.properties.http.HttpProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.azure.spring.core.AzureTokenCredentialResolver.DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME;


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
    public KeyVaultCertificateServiceClientBuilderFactory factory(@Qualifier(DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME) TokenCredential tokenCredential,
        KeyVaultProperties keyVaultProperties,
        KeyVaultCertificateProperties keyVaultCertificateProperties,
        HttpProperties httpProperties) {
        return new KeyVaultCertificateServiceClientBuilderFactory(tokenCredential,
            keyVaultProperties, keyVaultCertificateProperties, httpProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public CertificateClientBuilder secretClientBuilder(KeyVaultCertificateServiceClientBuilderFactory factory) {
        return factory.build();
    }
}
