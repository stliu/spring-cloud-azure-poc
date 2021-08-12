package com.azure.spring.autoconfigure.storage.blob;

import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.aware.AzureSasCredentialPropertiesAware;
import com.azure.spring.core.aware.ConnectionStringAware;
import com.azure.spring.core.aware.EndpointPropertiesAware;
import com.azure.spring.core.aware.HttpPropertiesAware;
import com.azure.spring.core.aware.SasCredentialAware;
import com.azure.spring.core.properties.StorageSharedKeyCredentialPropertiesAware;
import com.azure.spring.core.properties.http.HttpProperties;
import com.azure.storage.blob.BlobServiceVersion;
import com.azure.storage.blob.models.CustomerProvidedKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.azure.storage.blob")
public class AzureStorageBlob extends AzureProperties
    implements EndpointPropertiesAware,
    SasCredentialAware, AzureSasCredentialPropertiesAware, ConnectionStringAware,
    HttpPropertiesAware, InitializingBean {

    private HttpProperties http;

    private String endpoint;

    // sdk builder options
    private CustomerProvidedKey customerProvidedKey;
    private String encryptionScope;
    private String containerName;
    private String blobName;
    private String snapshot;
    private String versionId;
    private BlobServiceVersion serviceVersion;

    // StorageSharedKeyCredential
    private String accountName;
    private String accountKey;

    private String sasToken;

    // AzureSasCredential
    private String signature;

    private Boolean anonymousAccess;

    private String connectionString;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public HttpProperties getHttp() {
        return http;
    }

    @Override
    public void setHttp(HttpProperties http) {
        this.http = http;
    }

    public Boolean getAnonymousAccess() {
        return anonymousAccess;
    }

    public void setAnonymousAccess(Boolean anonymousAccess) {
        this.anonymousAccess = anonymousAccess;
    }

    public String getSasToken() {
        return sasToken;
    }

    @Override
    public void setSasToken(String sasToken) {
        this.sasToken = sasToken;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountKey() {
        return accountKey;
    }

    @Override
    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
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

    public void setServiceVersion(BlobServiceVersion serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public CustomerProvidedKey getCustomerProvidedKey() {
        return customerProvidedKey;
    }

    public void setCustomerProvidedKey(CustomerProvidedKey customerProvidedKey) {
        this.customerProvidedKey = customerProvidedKey;
    }

    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}
