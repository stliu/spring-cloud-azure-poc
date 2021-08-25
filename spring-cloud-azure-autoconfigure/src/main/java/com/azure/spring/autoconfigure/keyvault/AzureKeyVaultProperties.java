package com.azure.spring.autoconfigure.keyvault;

import com.azure.spring.core.properties.AzureProperties;

public class AzureKeyVaultProperties extends AzureProperties {

    private String vaultUrl;

    public String getVaultUrl() {
        return vaultUrl;
    }

    public void setVaultUrl(String vaultUrl) {
        this.vaultUrl = vaultUrl;
    }
}
