package com.azure.spring.autoconfigure.core;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpLogOptions;

import java.util.Optional;

public class SpringSDKServiceClientBuilderFactory<U extends AzureServiceClientBuilder<T>, T>
    implements HttpLogOptionsAware, HttpPipelineAware, SpringConnectionStringAware, SpringKeyCredentialAware, TokenCredentialAware {

    private U builder;
    private HttpLogOptions httpLogOptions;
    private HttpPipeline pipeline;
    private TokenCredential tokenCredential;
    private SpringKeyCredential springKeyCredential;
    private SpringConnectionString springConnectionString;

    public SpringSDKServiceClientBuilderFactory(U builder) {
        this.builder = builder;
    }

    public SpringSDKServiceClientBuilderFactory(Class buildClass) {
        try {
            this.builder = (U) buildClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public T create() {
        Optional.ofNullable(httpLogOptions).ifPresent(builder::httpLogOptions);
        Optional.ofNullable(pipeline).ifPresent(builder::pipeline);
        Optional.ofNullable(tokenCredential).ifPresent(builder::tokenCredential);
        Optional.ofNullable(springKeyCredential).ifPresent(builder::springKeyCredential);
        Optional.ofNullable(springConnectionString).ifPresent(builder::springConnectionString);
        return builder.build();
    }

    @Override
    public void setSpringConnectionString(SpringConnectionString springConnectionString) {
        this.springConnectionString = springConnectionString;
    }

    @Override
    public void setSpringKeyCredential(SpringKeyCredential springKeyCredential) {
        this.springKeyCredential = springKeyCredential;
    }

    @Override
    public void setHttpLogOptions(HttpLogOptions httpLogOptions) {
        this.httpLogOptions = httpLogOptions;
    }

    @Override
    public void setPipeline(HttpPipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public void setTokenCredential(TokenCredential credential) {
        this.tokenCredential = credential;
    }

    public Class builderClass() {
        return builder.getClass();
    }
}
