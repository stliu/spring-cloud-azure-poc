// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.core.properties;


/**
 * Azure properties used for getting token credential.
 */
public class CredentialProperties {

    private String keyOrResourceToken;

    private String connectionString;

    /**
     * Client id to use when performing service principal authentication with Azure.
     */
    private String clientId;

    /**
     * Client secret to use when performing service principal authentication with Azure.
     */
    private String clientSecret;

    /**
     * Path of a PEM certificate file to use when performing service principal authentication with Azure.
     */
    private String certificatePath;

    /**
     * Password of the certificate file.
     */
    private String certificatePassword;

    /**
     * Tenant id for the Azure resources.
     */
    private String tenantId;

    // StorageSharedKeyCredential
    private String accountName;
    private String accountKey;

    private String sasToken;

    // AzureSasCredential
    private String signature;

    private Boolean anonymousAccess;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getKeyOrResourceToken() {
        return keyOrResourceToken;
    }

    public void setKeyOrResourceToken(String keyOrResourceToken) {
        this.keyOrResourceToken = keyOrResourceToken;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getCertificatePassword() {
        return certificatePassword;
    }

    public void setCertificatePassword(String certificatePassword) {
        this.certificatePassword = certificatePassword;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
