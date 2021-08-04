package com.azure.spring.autoconfigure.core;

public class SpringConnectionString {

    Object storageBlobConnectionString;
    Object storageQueueConnectionString;

    SpringConnectionString(Object storageBlobConnectionString) {
        this.storageBlobConnectionString = storageBlobConnectionString;
    }
}
