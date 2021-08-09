package com.azure.spring.autoconfigure.storage.blob;

import com.azure.spring.core.AzureProperties;
import com.azure.spring.core.properties.ApplicationIdAware;
import com.azure.spring.core.properties.EndpointAware;
import com.azure.storage.blob.BlobServiceVersion;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.storage.blob")
public class AzureStorageBlobProperties extends AzureProperties implements EndpointAware, ApplicationIdAware, InitializingBean {

    // common http builder options
    private String endpoint;
    private String applicationId;

    // sdk builder options
    private CPK cpk;
    private String encryptionScope;
    private String containerName;
    private String blobName;
    private String snapshot;
    private String versionId;
    private BlobServiceVersion serviceVersion;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public static class CPK {

        /**
         * Base64 encoded string of the encryption key.
         */
        private String key;

        /**
         * Base64 encoded string of the encryption key's SHA256 hash.
         */
        private String keySha256;

        /**
         * The algorithm for Azure Blob Storage to encrypt with. Azure Blob Storage only offers AES256 encryption.
         */
        private String encryptionAlgorithm = "AES256";

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKeySha256() {
            return keySha256;
        }

        public void setKeySha256(String keySha256) {
            this.keySha256 = keySha256;
        }

        public String getEncryptionAlgorithm() {
            return encryptionAlgorithm;
        }

        public void setEncryptionAlgorithm(String encryptionAlgorithm) {
            this.encryptionAlgorithm = encryptionAlgorithm;
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getEncryptionScope() {
        return encryptionScope;
    }

    public void setEncryptionScope(String encryptionScope) {
        this.encryptionScope = encryptionScope;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
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

    public BlobServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    @Override
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setServiceVersion(BlobServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public CPK getCpk() {
        return cpk;
    }

    public void setCpk(CPK cpk) {
        this.cpk = cpk;
    }


}
