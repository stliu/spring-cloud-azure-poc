package com.azure.spring.autoconfigure.keyvault;

import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateAutoConfiguration;
import com.azure.spring.autoconfigure.keyvault.secret.KeyVaultSecretAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(KeyVaultProperties.class)
@ConditionalOnProperty(prefix = "spring.cloud.azure.keyvault", name = "enabled", matchIfMissing = true)
@Import({KeyVaultSecretAutoConfiguration.class, KeyVaultCertificateAutoConfiguration.class})
public class KeyVaultAutoConfiguration {


}
