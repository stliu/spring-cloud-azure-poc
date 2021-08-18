package com.azure.spring.autoconfigure.storage.properties;

import com.azure.spring.core.properties.AzureProperties;

public class AzureStorageProperties extends AzureProperties {

    // StorageSharedKeyCredential
    private String accountName;
    private String accountKey;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }
}
