package com.azure.spring.core.credential;

public interface AzureCredential<T> {

    AzureCredentialType getType();

    T getCredential();

}
