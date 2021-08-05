package com.azure.spring.autoconfigure.core;

import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateServiceClientBuilder;
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
    static final Map<Class, Class> MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING = new HashMap<>();

    static {
        MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.put(KeyVaultCertificateServiceClientBuilder.class, KeyVaultCertificateProperties.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
