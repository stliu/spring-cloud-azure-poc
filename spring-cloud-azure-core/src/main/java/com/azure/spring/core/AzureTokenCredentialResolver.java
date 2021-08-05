package com.azure.spring.core;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.ChainedTokenCredentialBuilder;

/**
 * Resolve the token credential according azure properties.
 */
public class AzureTokenCredentialResolver {

    public static final String DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME = "defaultChainedTokenCredential";

    /**
     * Resolve the final token credential, this is combined the starter inherited credential and azure spring default credential.
     * @param defaultTokenCredential
     * @param azureProperties
     * @return
     */
    public TokenCredential resolve(TokenCredential defaultTokenCredential, AzureProperties azureProperties) {
        final ChainedTokenCredentialBuilder builder = new ChainedTokenCredentialBuilder();
        TokenCredential inheritedTokenCredential = getPropertiesInheritedTokenCredential(azureProperties);
        builder.addLast(inheritedTokenCredential).addLast(defaultTokenCredential);
        return builder.build();
    }

    private TokenCredential getPropertiesInheritedTokenCredential(AzureProperties azureProperties) {


        // create ChainedTokenCredential according the modular AzureProperties
        return null;
    }

}
