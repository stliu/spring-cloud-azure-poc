package com.azure.spring.core.context;

import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.http.HttpProperties;
import org.springframework.core.env.Environment;

/**
 * Default implementation for http configuration context,suitable for module-based http protocol.
 */
public class DefaultAzureSpringHttpConfigurationContext implements AzureSpringHttpConfigurationContext {

    private final TokenCredential tokenCredential;
    private final AzureProperties azureProperties;
    private final Environment environment;

    public DefaultAzureSpringHttpConfigurationContext(TokenCredential tokenCredential,
                                                      AzureProperties azureProperties,
                                                      Environment environment) {
        this.tokenCredential = tokenCredential;
        this.azureProperties = azureProperties;
        this.environment = environment;
    }

//    public HttpProperties getInheritHttpProperties(AzureProperties inheritProperties) {
//
//        return null;
//    }
//
//    public CredentialProperties getInheritCredentialProperties(AzureProperties inheritProperties) {
//
//        return null;
//    }

    @Override
    public AzureProperties getRootAzureProperties() {
        return azureProperties;
    }

    @Override
    public HttpProperties getRootHttpProperties() {
        return null;
    }

    @Override
    public TokenCredential getTokenCredential() {
        return tokenCredential;
    }
}
