package com.azure.spring.core.credential.resolver;

import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.properties.AzureProperties;

public interface AzureCredentialResolver<AzureCredential> {

    AzureCredential resolve(AzureProperties azureProperties);

    AzureCredentialType support();

}
