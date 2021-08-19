package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.spring.core.properties.AzureProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault.certificate")
public class KeyVaultCertificateProperties extends AzureProperties {

}
