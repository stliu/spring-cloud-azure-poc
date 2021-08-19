// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.core.properties;

import com.azure.spring.core.properties.client.ClientProperties;
import com.azure.spring.core.properties.credential.TokenCredentialProperties;

/**
 * Unified properties for Azure SDK clients.
 */
public class AzureProperties {

    protected ClientProperties client;

    protected EnvironmentProperties env;

    protected ProxyProperties proxy;

    protected RetryProperties retry;

    protected TokenCredentialProperties credential;

    public ClientProperties getClient() {
        return client;
    }

    public void setClient(ClientProperties client) {
        this.client = client;
    }

    public EnvironmentProperties getEnv() {
        return env;
    }

    public void setEnv(EnvironmentProperties env) {
        this.env = env;
    }

    public ProxyProperties getProxy() {
        return proxy;
    }

    public void setProxy(ProxyProperties proxy) {
        this.proxy = proxy;
    }

    public RetryProperties getRetry() {
        return retry;
    }

    public void setRetry(RetryProperties retry) {
        this.retry = retry;
    }

    public TokenCredentialProperties getCredential() {
        return credential;
    }

    public void setCredential(TokenCredentialProperties credential) {
        this.credential = credential;
    }
}
