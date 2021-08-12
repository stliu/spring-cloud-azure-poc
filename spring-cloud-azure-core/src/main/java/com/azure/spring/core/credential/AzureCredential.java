package com.azure.spring.core.credential;

public interface AzureCredential<T> {

    Class<T> getType();

    T getCredential();

}
