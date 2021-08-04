package com.azure.spring.autoconfigure.core;

public class SpringConnectionStringBuilder {

    Object storageBlobConnectionString;
    Object storageQueueConnectionString;

    SpringConnectionStringBuilder storageBlobConnectionString(Object storageBlobConnectionString) {
        this.storageBlobConnectionString = storageBlobConnectionString;
        return this;
    }

    SpringConnectionStringBuilder storageQueueAzureKeyCredential(Object storageQueueConnectionString) {
        this.storageQueueConnectionString = storageQueueConnectionString;
        return this;
    }

    public SpringConnectionString build() {
        if (storageBlobConnectionString != null) {
            return new SpringConnectionString(storageBlobConnectionString);
        } else {
            return new SpringConnectionString(storageQueueConnectionString);
        }
    }
}
