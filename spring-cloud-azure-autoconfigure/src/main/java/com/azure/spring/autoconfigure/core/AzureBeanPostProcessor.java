package com.azure.spring.autoconfigure.core;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.identity.ChainedTokenCredentialBuilder;
import com.azure.spring.core.TokenCredentialAware;
import com.azure.spring.core.http.HttpPipelineAware;
import com.azure.spring.core.identify.AzureProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AzureBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Class modularPropertiesClass;
    private Class builderFactoryClass;
    public static final String DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME = "defaultChainedTokenCredential";

    public AzureBeanPostProcessor(Class modularPropertiesClass, Class builderFactoryClass) {
        this.modularPropertiesClass = modularPropertiesClass;
        this.builderFactoryClass = builderFactoryClass;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (builderFactoryClass != bean.getClass()) {
            return bean;
        }

        // default logic

        return bean;
    }

    public void awareSpringConnectionString(Object bean) {

    }

    public void awareSpringKeyCredential(Object bean) {

    }

    public void awareHttpPipeline(Object bean) {
        if (bean instanceof HttpPipelineAware) {
//            KeyVaultCertificateProperties properties = getKeyVaultCertificateProperties();
            HttpPipeline pipeline = new HttpPipelineBuilder().build();
            ((HttpPipelineAware) bean).setPipeline(pipeline);
        }
    }

    public void awareTokenCredential(Object bean) {
        if (bean instanceof TokenCredentialAware) {
            TokenCredential defaultTokenCredential = applicationContext.getBean(
                DEFAULT_CHAINED_TOKEN_CREDENTIAL_BEAN_NAME, TokenCredential.class);
            final ChainedTokenCredentialBuilder chainedTokenCredentialBuilder = new ChainedTokenCredentialBuilder();
            TokenCredential inheritedTokenCredential = getPropertiesInheritedTokenCredential();
            chainedTokenCredentialBuilder.addLast(inheritedTokenCredential)
                                         .addLast(defaultTokenCredential);
            ((TokenCredentialAware) bean).setTokenCredential(chainedTokenCredentialBuilder.build());
        }
    }


    private TokenCredential getPropertiesInheritedTokenCredential() {
        AzureProperties bean = (AzureProperties) applicationContext.getBean(modularPropertiesClass);

        // create ChainedTokenCredential according the modular AzureProperties
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
