package com.azure.spring.autoconfigure.core;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpLogOptions;

import java.util.List;
import java.util.Optional;

public class AzureSDKServiceClientBuilderFactory<U extends AzureServiceClientBuilder<T>, T>
    implements HttpLogOptionsAware, HttpPipelineAware, ConnectionStringAware, AzureKeyCredentialAware, TokenCredentialAware {

    private U builder;
    private List<ServiceClientBuilderCustomizer<U>> builderCustomizerList;

    private HttpLogOptions httpLogOptions;
    private HttpPipeline pipeline;
    private TokenCredential tokenCredential;
    private SpringKeyCredential springKeyCredential;
    private SpringConnectionString springConnectionString;

//    public AzureSDKServiceClientBuilderFactory(U builder,
//                                               List<ServiceClientBuilderCustomizer<U>> builderCustomizerList) {
//        this.builder = builder;
//
//        this.builderCustomizerList = builderCustomizerList;
//    }

    public AzureSDKServiceClientBuilderFactory(Class buildClass, List<ServiceClientBuilderCustomizer<U>> builderCustomizerList) {
        try {
            this.builder = (U) buildClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        this.builderCustomizerList = builderCustomizerList;
    }

    public T create() {
        Optional.ofNullable(builderCustomizerList).ifPresent(list -> list.forEach(c -> c.customize(builder)));
        Optional.ofNullable(httpLogOptions).ifPresent(builder::httpLogOptions);
        Optional.ofNullable(pipeline).ifPresent(builder::pipeline);
        Optional.ofNullable(tokenCredential).ifPresent(builder::tokenCredential);
        Optional.ofNullable(springKeyCredential).ifPresent(builder::springKeyCredential);
        Optional.ofNullable(springConnectionString).ifPresent(builder::springConnectionString);
        return builder.build();
    }

    @Override
    public void setConnectionString(SpringConnectionString springConnectionString) {
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
}
