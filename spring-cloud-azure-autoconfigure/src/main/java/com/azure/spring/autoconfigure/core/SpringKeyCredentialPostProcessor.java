package com.azure.spring.autoconfigure.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SpringKeyCredentialPostProcessor extends AbstractSpringPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof SpringKeyCredentialAware)) {
            return bean;
        }

        SpringSDKServiceClientBuilderFactory factory = (SpringSDKServiceClientBuilderFactory) bean;
        if (!MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.containsKey(factory.builderClass())) {
            return bean;
        }

        Class propertiesClass = MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.get(factory.builderClass());
        SpringKeyCredentialBuilder keyCredentialBuilder = new SpringKeyCredentialBuilder();
        // build pipeline options.
        ((SpringKeyCredentialAware) bean).setSpringKeyCredential(keyCredentialBuilder.build());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
