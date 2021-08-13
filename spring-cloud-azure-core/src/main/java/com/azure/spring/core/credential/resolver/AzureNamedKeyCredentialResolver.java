package com.azure.spring.core.credential.resolver;

import com.azure.core.credential.AzureNamedKeyCredential;
import com.azure.spring.core.aware.credential.NamedKeyAware;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialType;
import com.azure.spring.core.credential.wrapper.AzureNamedKeyCredentialWrapper;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.credential.NamedKeyProperties;
import org.springframework.util.StringUtils;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureNamedKeyCredentialResolver implements AzureCredentialResolver<AzureCredential<AzureNamedKeyCredential>> {

    @Override
    public AzureCredential<AzureNamedKeyCredential> resolve(AzureProperties azureProperties) {
        if (!(azureProperties instanceof NamedKeyAware)) {
            return null;
        }

        NamedKeyProperties namedKey = ((NamedKeyAware) azureProperties).getNamedKey();
        if (!StringUtils.hasText(namedKey.getName()) || !StringUtils.hasText(namedKey.getKey())) {
            return null;
        }

        return new AzureNamedKeyCredentialWrapper(namedKey.getName(), namedKey.getKey());
    }

    @Override
    public AzureCredentialType support() {
        return AzureCredentialType.NAMED_KEY_CREDENTIAL;
    }

}
