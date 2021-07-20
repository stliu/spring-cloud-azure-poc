package com.azure.spring.autoconfigure.storage.blob;

import com.azure.spring.autoconfigure.core.ServiceVersionProvider;
import com.azure.storage.blob.BlobServiceVersion;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.storage.blob")
public class AzureStorageBlobProperties implements ServiceVersionProvider<BlobServiceVersion> {

    private CPK cpk;
    private BlobServiceVersion serviceVersion;

    @Override
    public BlobServiceVersion getServiceVersion() {
        return serviceVersion;
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

}
