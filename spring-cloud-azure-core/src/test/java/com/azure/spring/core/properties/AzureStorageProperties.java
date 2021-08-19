package com.azure.spring.core.properties;

import com.azure.spring.core.properties.aware.credential.ConnectionStringAware;
import com.azure.spring.core.properties.aware.credential.SasTokenAware;

public class AzureStorageProperties extends AzureProperties implements ConnectionStringAware,
                                                                       SasTokenAware {

    // TODO
    protected String accountName;

    protected String accountKey;

    protected String sasToken;

    protected String connectionString;

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

    @Override
    public String getSasToken() {
        return sasToken;
    }

    @Override
    public void setSasToken(String sasToken) {
        this.sasToken = sasToken;
    }

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}
