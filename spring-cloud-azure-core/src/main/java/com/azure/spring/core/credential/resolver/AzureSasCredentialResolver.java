package com.azure.spring.core.credential.resolver;

import com.azure.core.credential.AzureSasCredential;
import com.azure.spring.core.aware.credential.SasTokenAware;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.wrapper.AzureSasCredentialWrapper;
import com.azure.spring.core.properties.AzureProperties;
import org.springframework.util.StringUtils;

/**
 * Resolve the sas token credential according azure properties.
 */
public class AzureSasCredentialResolver implements AzureCredentialResolver<AzureCredential<AzureSasCredential>> {

    @Override
    public AzureCredential<AzureSasCredential> resolve(AzureProperties azureProperties) {
        if (!(azureProperties instanceof SasTokenAware)) {
            return null;
        }

        String sasToken = ((SasTokenAware) azureProperties).getSasToken();
        if (!StringUtils.hasText(sasToken)) {
            return null;
        }

        return new AzureSasCredentialWrapper(sasToken);
    }

    @Override
    public AzureCredentialType support() {
        return AzureCredentialType.SAS_CREDENTIAL;
    }
}
