// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.spring.autoconfigure.cosmos;

import com.azure.cosmos.ConnectionMode;
import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.cosmos.models.CosmosPermissionProperties;
import com.azure.spring.core.AzureProperties;
import com.azure.spring.core.properties.ApplicationIdAware;
import com.azure.spring.core.properties.EndpointAware;
import com.azure.spring.core.properties.ProxyProperties;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * Configuration properties for Cosmos database, consistency, telemetry, connection, query metrics and diagnostics.
 */
@Validated
@ConfigurationProperties(CosmosProperties.PREFIX)
public class CosmosProperties extends AzureProperties implements
    EndpointAware, ApplicationIdAware, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(CosmosProperties.class);
    public static final String PREFIX = "spring.cloud.azure.cosmos";

    private String endpoint;
    private String applicationId;

    private ProxyProperties proxy;

    /**
     * Override enabled, session capturing is enabled by default for {@link ConsistencyLevel#SESSION}
     */
    private boolean sessionCapturingOverrideEnabled;
    private boolean connectionSharingAcrossClientsEnabled;
    private boolean contentResponseOnWriteEnabled;
    private CosmosPermissionProperties permissions;
    private GatewayConnectionConfig gatewayConnectionConfig;
    private DirectConnectionConfig directConnectionConfig;

    /**
     * Document DB consistency level.
     */
    private ConsistencyLevel consistencyLevel;

    /**
     * Document DB database name.
     */
    @NotEmpty
    private String database;

    /**
     * Populate Diagnostics Strings and Query metrics
     */
    private boolean populateQueryMetrics;

    /**
     * Whether allow Microsoft to collect telemetry data.
     */
    private boolean allowTelemetry = true;

    /**
     * Represents the connection mode to be used by the client in the Azure Cosmos DB database service.
     */
    private ConnectionMode connectionMode;

    /**
     * Response Diagnostics processor
     * Default implementation is to log the response diagnostics string
     */
    private ResponseDiagnosticsProcessor responseDiagnosticsProcessor =
        responseDiagnostics -> {
            if (populateQueryMetrics) {
                LOGGER.info("Response Diagnostics {}", responseDiagnostics);
            }
        };

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO: DB endpoint should be not empty
    }

    public String getApplicationId() {
        return applicationId;
    }

    @Override
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public ProxyProperties getProxy() {
        return proxy;
    }

    public void setProxy(ProxyProperties proxy) {
        this.proxy = proxy;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String databaseName) {
        this.database = databaseName;
    }

    public ConsistencyLevel getConsistencyLevel() {
        return consistencyLevel;
    }

    public void setConsistencyLevel(ConsistencyLevel consistencyLevel) {
        this.consistencyLevel = consistencyLevel;
    }

    @Deprecated
    @DeprecatedConfigurationProperty(
        reason = "Deprecate the telemetry endpoint and use HTTP header User Agent instead.")
    public boolean isAllowTelemetry() {
        return allowTelemetry;
    }

    public void setAllowTelemetry(boolean allowTelemetry) {
        this.allowTelemetry = allowTelemetry;
    }

    public boolean isPopulateQueryMetrics() {
        return populateQueryMetrics;
    }

    public void setPopulateQueryMetrics(boolean populateQueryMetrics) {
        this.populateQueryMetrics = populateQueryMetrics;
    }

    public ResponseDiagnosticsProcessor getResponseDiagnosticsProcessor() {
        return responseDiagnosticsProcessor;
    }

    public void setResponseDiagnosticsProcessor(ResponseDiagnosticsProcessor responseDiagnosticsProcessor) {
        this.responseDiagnosticsProcessor = responseDiagnosticsProcessor;
    }

    public ConnectionMode getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(ConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
    }
}
