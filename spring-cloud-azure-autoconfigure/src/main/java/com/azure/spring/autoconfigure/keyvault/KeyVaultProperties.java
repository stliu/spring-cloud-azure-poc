package com.azure.spring.autoconfigure.keyvault;

import com.azure.spring.core.properties.AzureProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.keyvault")
public class KeyVaultProperties extends AzureProperties {

    private String endpoint;


}
