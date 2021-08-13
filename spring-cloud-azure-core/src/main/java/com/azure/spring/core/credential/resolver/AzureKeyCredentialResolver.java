package com.azure.spring.core.credential.resolver;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.spring.core.aware.credential.KeyAware;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.wrapper.AzureKeyCredentialWrapper;
import com.azure.spring.core.properties.AzureProperties;
import org.springframework.util.StringUtils;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureKeyCredentialResolver implements AzureCredentialResolver<AzureCredential<AzureKeyCredential>> {

    @Override
    public AzureCredential<AzureKeyCredential> resolve(AzureProperties azureProperties) {
        if (!(azureProperties instanceof KeyAware)) {
            return null;
        }

        String key = ((KeyAware) azureProperties).getKey();
        if (!StringUtils.hasText(key)) {
            return null;
        }

        return new AzureKeyCredentialWrapper(key);
    }

    @Override
    public AzureCredentialType support() {
        return AzureCredentialType.KEY_CREDENTIAL;
    }

}
