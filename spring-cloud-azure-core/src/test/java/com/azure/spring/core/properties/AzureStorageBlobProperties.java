package com.azure.spring.core.properties;

import com.azure.storage.blob.BlobServiceVersion;
import com.azure.storage.blob.models.CustomerProvidedKey;
import org.springframework.boot.context.properties.ConfigurationProperties;

public class AzureStorageBlobProperties extends AzureStorageProperties {


    private boolean anonymousAccess;
    private String blobName;
    private String containerName;
    private CustomerProvidedKey customerProvidedKey;
    private String encryptionScope;
    private String endpoint;
    private BlobServiceVersion serviceVersion;
    private String snapshot;
    private String versionId;

    public boolean isAnonymousAccess() {
        return anonymousAccess;
    }

    public void setAnonymousAccess(boolean anonymousAccess) {
        this.anonymousAccess = anonymousAccess;
    }

    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public CustomerProvidedKey getCustomerProvidedKey() {
        return customerProvidedKey;
    }

    public void setCustomerProvidedKey(CustomerProvidedKey customerProvidedKey) {
        this.customerProvidedKey = customerProvidedKey;
    }

    public String getEncryptionScope() {
        return encryptionScope;
    }

    public void setEncryptionScope(String encryptionScope) {
        this.encryptionScope = encryptionScope;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public BlobServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(BlobServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
