package com.azure.spring.core.context;

import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.context.AzureSpringConfigurationContext;
import com.azure.spring.core.properties.http.HttpProperties;

/**
 * Common http configuration context of module-based http protocol.
 */
public interface AzureSpringHttpConfigurationContext extends AzureSpringConfigurationContext {

    AzureProperties getRootAzureProperties();

    HttpProperties getRootHttpProperties();
}
