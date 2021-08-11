package com.azure.spring.core;

import com.azure.core.credential.TokenCredential;
import com.azure.core.util.Configuration;

/**
 * Common azure spring configuration context of all modules.
 */
public interface AzureSpringConfigurationContext {


    default Configuration getAzureDefaultConfiguration() {
        return Configuration.getGlobalConfiguration().clone();
    }

    TokenCredential getTokenCredential();
}
