package com.azure.spring.autoconfigure.core;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.ChainedTokenCredentialBuilder;
import com.azure.spring.core.TokenCredentialAware;
import com.azure.spring.core.identify.AzureProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class TokenCredentialSpringPostProcessor extends AbstractSpringPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof TokenCredentialAware)) {
            return bean;
        }

        SpringSDKServiceClientBuilderFactory factory = (SpringSDKServiceClientBuilderFactory) bean;
        if (!MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.containsKey(factory.builderClass())) {
            return bean;
        }

        Class propertiesClass = MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.get(factory.builderClass());
        final ChainedTokenCredentialBuilder chainedTokenCredentialBuilder = new ChainedTokenCredentialBuilder();
        TokenCredential inheritedTokenCredential = getPropertiesInheritedTokenCredential(propertiesClass);
        TokenCredential defaultTokenCredential = applicationContext.getBean(
            DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME, TokenCredential.class);
        chainedTokenCredentialBuilder.addLast(inheritedTokenCredential)
                                     .addLast(defaultTokenCredential);
        ((TokenCredentialAware) bean).setTokenCredential(chainedTokenCredentialBuilder.build());
        return bean;
    }

    private TokenCredential getPropertiesInheritedTokenCredential(Class propertiesClass) {
        AzureProperties bean = (AzureProperties) applicationContext.getBean(propertiesClass);

        // create ChainedTokenCredential according the modular AzureProperties
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
