package com.azure.spring.autoconfigure.core;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.spring.core.http.HttpPipelineAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class HttpPipelinePostProcessor extends AbstractSpringPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof HttpPipelineAware)) {
            return bean;
        }

        SpringSDKServiceClientBuilderFactory factory = (SpringSDKServiceClientBuilderFactory) bean;
        if (!MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.containsKey(factory.builderClass())) {
            return bean;
        }

        Class propertiesClass = MODULAR_BUILDER_PROPERTIES_CLASS_MAPPING.get(factory.builderClass());
        HttpPipelineBuilder httpPipelineBuilder = new HttpPipelineBuilder();
        // build pipeline options.
        ((HttpPipelineAware) bean).setPipeline(httpPipelineBuilder.build());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
