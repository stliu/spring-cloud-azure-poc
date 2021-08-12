package com.azure.spring.core.credential;

public interface AzureCredentialResolver<AzureCredential> {

    AzureCredential resolve();

}
