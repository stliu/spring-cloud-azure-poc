package com.azure.spring.core.credential.wrapper;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.AzureSasCredential;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;

public class AzureKeyCredentialWrapper implements AzureCredential<AzureKeyCredential> {

    private String key;

    public AzureKeyCredentialWrapper(String key) {
        this.key = key;
    }

    @Override
    public AzureCredentialType getType() {
        return AzureCredentialType.KEY_CREDENTIAL;
    }

    @Override
    public AzureKeyCredential getCredential() {
        AzureKeyCredential keyCredential = new AzureKeyCredential(key);
        return keyCredential;
    }
}
