package com.azure.spring.core.http;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.HttpPipelinePolicy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AzureHttpPipelineFactoryBean extends AbstractFactoryBean<HttpPipeline> implements ApplicationContextAware {

    private  HttpClient httpClient;
    private  HttpPipelinePolicy[] policies;
    private ApplicationContext applicationContext;

    @Override
    public Class<?> getObjectType() {
        return HttpPipeline.class;
    }

    @Override
    protected HttpPipeline createInstance() throws Exception {
        HttpPipelineBuilder builder = new HttpPipelineBuilder();
        builder.build();
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();


    }

    @Override
    public void destroy() throws Exception {
        super.destroy();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
