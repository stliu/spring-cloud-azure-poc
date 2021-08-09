package com.azure.spring.core.http;

import com.azure.spring.core.AzureSpringConfigurationContext;
import com.azure.spring.core.properties.HttpProperties;
import com.azure.spring.core.properties.ProxyProperties;

/**
 * Common http configuration context of module-based http protocol.
 */
public interface AzureSpringHttpConfigurationContext extends AzureSpringConfigurationContext {

    HttpProperties getHttpProperties();

    ProxyProperties getProxyProperties();

}
