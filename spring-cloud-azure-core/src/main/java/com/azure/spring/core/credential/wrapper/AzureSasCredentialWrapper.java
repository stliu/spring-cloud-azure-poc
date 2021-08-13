package com.azure.spring.core.credential.wrapper;

import com.azure.core.credential.AzureSasCredential;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;

public class AzureSasCredentialWrapper implements AzureCredential<AzureSasCredential> {

    private String sasToken;

    public AzureSasCredentialWrapper(String sasToken) {
        this.sasToken = sasToken;
    }

    @Override
    public AzureCredentialType getType() {
        return AzureCredentialType.SAS_CREDENTIAL;
    }

    @Override
    public AzureSasCredential getCredential() {
        AzureSasCredential sasCredential = new AzureSasCredential(sasToken);
        return sasCredential;
    }
}
