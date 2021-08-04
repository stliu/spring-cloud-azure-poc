package com.azure.spring.autoconfigure.core;

/**
 * Unify Azure SDK Azure Key Credential usage.
 */
public class SpringKeyCredential {

    Object storageBlobAzureKeyCredential;
    Object storageQueueAzureKeyCredential;

    SpringKeyCredential(Object storageQueueAzureKeyCredential) {
        this.storageQueueAzureKeyCredential = storageQueueAzureKeyCredential;
    }

//    SpringKeyCredential(Object storageBlobAzureKeyCredential) {
//        this.storageBlobAzureKeyCredential = storageBlobAzureKeyCredential;
//    }
}
