// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.spring.core.properties;

/**
 * Common Azure environment properties, such as Azure Cloud, for all Azure SDKs.
 */
public class EnvironmentProperties {

    private String cloud;

    private String authorityHost;

    private String graphBaseUri;

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getAuthorityHost() {
        return authorityHost;
    }

    public void setAuthorityHost(String authorityHost) {
        this.authorityHost = authorityHost;
    }

    public String getGraphBaseUri() {
        return graphBaseUri;
    }

    public void setGraphBaseUri(String graphBaseUri) {
        this.graphBaseUri = graphBaseUri;
    }

    //TODO: transfer cloud to AzureEnvironment
}
