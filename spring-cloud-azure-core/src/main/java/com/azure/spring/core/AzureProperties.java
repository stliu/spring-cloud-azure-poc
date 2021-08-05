// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.core;

import com.azure.spring.core.identify.CredentialProperties;
import com.azure.spring.core.identify.EnvironmentProperties;

/**
 * Unified properties for Azure SDK clients.
 */
public class AzureProperties {

    public static final String AZURE_PROPERTY_BEAN_NAME = "azureProperties";

    public static final String PREFIX = "spring.cloud.azure";

    private CredentialProperties credential;

    private EnvironmentProperties environment;

    public AzureProperties() {
        this.credential = null;
        this.environment = null;
    }

    public CredentialProperties getCredential() {
        return credential;
    }

    public void setCredential(CredentialProperties credential) {
        this.credential = credential;
    }

    public EnvironmentProperties getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentProperties environment) {
        this.environment = environment;
    }
}
