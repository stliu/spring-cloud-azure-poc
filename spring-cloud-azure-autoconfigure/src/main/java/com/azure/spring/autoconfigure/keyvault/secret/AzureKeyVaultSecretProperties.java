package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.spring.core.properties.AzureProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault.secret")
public class AzureKeyVaultSecretProperties extends AzureProperties {

}
