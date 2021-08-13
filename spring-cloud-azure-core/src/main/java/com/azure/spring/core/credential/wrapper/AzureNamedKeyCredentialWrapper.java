package com.azure.spring.core.credential.wrapper;

import com.azure.core.credential.AzureNamedKeyCredential;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;

public class AzureNamedKeyCredentialWrapper implements AzureCredential<AzureNamedKeyCredential> {

    private String name;
    private String key;

    public AzureNamedKeyCredentialWrapper(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public AzureCredentialType getType() {
        return AzureCredentialType.NAMED_KEY_CREDENTIAL;
    }

    @Override
    public AzureNamedKeyCredential getCredential() {
        return new AzureNamedKeyCredential(name, key);
    }
}
