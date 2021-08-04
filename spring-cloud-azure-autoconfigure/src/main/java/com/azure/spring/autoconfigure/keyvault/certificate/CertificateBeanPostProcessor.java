package com.azure.spring.autoconfigure.keyvault.certificate;

import com.azure.spring.autoconfigure.core.AzureBeanPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class CertificateBeanPostProcessor extends AzureBeanPostProcessor {

    private ApplicationContext applicationContext;

    public CertificateBeanPostProcessor(Class modularPropertiesClass, Class builderFactoryClass) {
        super(modularPropertiesClass, builderFactoryClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        super.postProcessBeforeInitialization(bean, beanName);

        awareTokenCredential(bean);

        // AzureKeyCredential

        awareHttpPipeline(bean);
        return bean;
    }
}
