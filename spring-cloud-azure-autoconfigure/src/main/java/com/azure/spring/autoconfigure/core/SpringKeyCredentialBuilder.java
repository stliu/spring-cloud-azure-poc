package com.azure.spring.autoconfigure.core;

public class SpringKeyCredentialBuilder {

    Object storageBlobAzureKeyCredential;
    Object storageQueueAzureKeyCredential;

    SpringKeyCredentialBuilder storageBlobAzureKeyCredential(Object storageBlobAzureKeyCredential) {
        this.storageBlobAzureKeyCredential = storageBlobAzureKeyCredential;
        return this;
    }

    SpringKeyCredentialBuilder storageQueueAzureKeyCredential(Object storageQueueAzureKeyCredential) {
        this.storageQueueAzureKeyCredential = storageQueueAzureKeyCredential;
        return this;
    }

    public SpringKeyCredential build() {
        if (storageBlobAzureKeyCredential != null) {
            return new SpringKeyCredential(storageBlobAzureKeyCredential);
        } else {
            return new SpringKeyCredential(storageQueueAzureKeyCredential);
        }
    }
}
