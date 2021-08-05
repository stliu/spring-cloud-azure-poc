package com.azure.spring.autoconfigure.core;

import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateServiceClientBuilderFactory;
import com.azure.spring.autoconfigure.keyvault.secret.KeyVaultSecretServiceClientBuilderFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for azure spring common bean post processor.
 */
public abstract class AbstractSpringPostProcessor implements ApplicationContextAware {

    ApplicationContext applicationContext;
    static final String DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME = "defaultChainedTokenCredential";
    /**
     * Client builder class and the properties class which extends AzureProperties.
     */
    static final Map<Class, Class> MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING = new HashMap<>();

    static {
        MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.put(KeyVaultCertificateServiceClientBuilderFactory.class, KeyVaultCertificateProperties.class);
        MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.put(KeyVaultSecretServiceClientBuilderFactory.class, KeyVaultCertificateProperties.class);
        // other relationships
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
