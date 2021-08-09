// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.core;

import com.azure.spring.core.identify.EnvironmentProperties;
import com.azure.spring.core.properties.CredentialProperties;
import com.azure.spring.core.properties.HttpProperties;
import com.azure.spring.core.properties.ProxyProperties;

/**
 * Unified properties for Azure SDK clients.
 */
public class AzureProperties {

    public static final String AZURE_PROPERTY_BEAN_NAME = "azureProperties";

    public static final String PREFIX = "spring.cloud.azure";

    private HttpProperties http;

    private CredentialProperties credential;

    private EnvironmentProperties environment;

    private ProxyProperties proxy;

    public HttpProperties getHttp() {
        return http;
    }

    public void setHttp(HttpProperties http) {
        this.http = http;
    }

    public ProxyProperties getProxy() {
        return proxy;
    }

    public void setProxy(ProxyProperties proxy) {
        this.proxy = proxy;
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
