package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.security.keyvault.certificates.CertificateAsyncClient;
import com.azure.security.keyvault.certificates.CertificateClient;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.spring.autoconfigure.core.AzureSDKServiceClientBuilderFactory;
import com.azure.spring.autoconfigure.core.AzureServiceClientBuilder;
import com.azure.spring.autoconfigure.core.ServiceClientBuilderCustomizer;
import com.azure.spring.autoconfigure.keyvault.KeyVaultAutoConfiguration;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

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

    /*@Bean
    @ConditionalOnMissingBean
    public CertificateClientBuilder secretClientBuilder(KeyVaultProperties keyVaultProperties,
        KeyVaultCertificateProperties keyVaultCertificateProperties,
        ObjectProvider<KeyVaultCertificateBuilderCustomizer> configurers) {
        CertificateClientBuilder builder = new CertificateClientBuilder();
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();

        map.from(keyVaultProperties.getEndpoint()).to(builder::vaultUrl);
        map.from(keyVaultCertificateProperties.getServiceVersion()).to(builder::serviceVersion);
        configurers.orderedStream().forEach(c -> c.customize(builder));
        return builder;
    }*/

    @Bean
    public ServiceClientBuilderCustomizer<KeyVaultCertificateServiceClientBuilder> serviceClientBuilderCustomizer(KeyVaultProperties keyVaultProperties,
                                                                                                         KeyVaultCertificateProperties keyVaultCertificateProperties) {
        return builder -> {
            PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
            map.from(keyVaultProperties.getEndpoint()).to(builder::vaultUrl);
            map.from(keyVaultCertificateProperties.getServiceVersion()).to(builder::serviceVersion);
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public AzureSDKServiceClientBuilderFactory<? extends AzureServiceClientBuilder<CertificateClientBuilder>, CertificateClientBuilder>
        certificateServiceClientBuilderFactory(ObjectProvider<ServiceClientBuilderCustomizer<KeyVaultCertificateServiceClientBuilder>> customizers) {
        return new AzureSDKServiceClientBuilderFactory<>(KeyVaultCertificateServiceClientBuilder.class, customizers.orderedStream().collect(Collectors.toList()));
    }

    @Bean
    @ConditionalOnMissingBean
    public CertificateClientBuilder secretClientBuilder(AzureSDKServiceClientBuilderFactory<? extends AzureServiceClientBuilder<CertificateClientBuilder>, CertificateClientBuilder> factory) {
        return factory.create();
    }

    @Bean
    @ConditionalOnMissingBean
    public CertificateBeanPostProcessor certificateBeanPostProcessor() {
        return new CertificateBeanPostProcessor(KeyVaultCertificateProperties.class, AzureSDKServiceClientBuilderFactory.class);
    }
}
