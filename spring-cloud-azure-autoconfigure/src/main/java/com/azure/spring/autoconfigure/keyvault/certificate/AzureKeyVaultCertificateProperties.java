package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.spring.autoconfigure.keyvault.AzureKeyVaultProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault.certificate")
public class AzureKeyVaultCertificateProperties extends AzureKeyVaultProperties {

}
