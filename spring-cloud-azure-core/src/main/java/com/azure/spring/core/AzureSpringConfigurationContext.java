package com.azure.spring.core;

import com.azure.core.credential.TokenCredential;

/**
 * Common azure spring configuration context of all modules.
 */
public interface AzureSpringConfigurationContext {

    AzureProperties getRootAzureProperties();
    TokenCredential getTokenCredential();
}
