package com.azure.spring.autoconfigure.keyvault.secret;

import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.spring.autoconfigure.core.ServiceClientBuilderCustomizer;

public class KeyVaultSecretBuilderCustomizer implements ServiceClientBuilderCustomizer<SecretClientBuilder> {

    public void customize(SecretClientBuilder builder) {

    }
}
