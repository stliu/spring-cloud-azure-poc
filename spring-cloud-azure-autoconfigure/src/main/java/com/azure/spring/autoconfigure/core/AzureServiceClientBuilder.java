package com.azure.spring.autoconfigure.core;

import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.policy.HttpLogOptions;

/**
 * Wrapper the Azure SDK service client builder.
 * @param <T>
 */
public interface AzureServiceClientBuilder<T> {

    T build();

    void httpLogOptions(HttpLogOptions logOptions);
    void pipeline(HttpPipeline pipeline);
    void springConnectionString(SpringConnectionString springConnectionString);
    void springKeyCredential(SpringKeyCredential springKeyCredential);
    void tokenCredential(TokenCredential credential);

}
