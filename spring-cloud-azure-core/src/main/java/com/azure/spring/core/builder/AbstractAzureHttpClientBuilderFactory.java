package com.azure.spring.core.builder;

import com.azure.core.http.ProxyOptions;
import com.azure.core.util.HttpClientOptions;
import com.azure.spring.core.properties.AzureProperties;
import com.azure.spring.core.properties.ProxyProperties;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;

public abstract class AbstractAzureHttpClientBuilderFactory<T> extends AbstractAzureServiceClientBuilderFactory<T> {

    protected AzureProperties azureProperties;

    private HttpClientOptions httpClientOptions = new HttpClientOptions();

    protected abstract T internalBuild();

    protected abstract void internalSetHttpClientOptions(HttpClientOptions httpClientOptions);

    /**


     1. create a builder instance
     2. configure builder
        2.1 configure azure core level configuration
            2.1.1 configure http client
                    getHttpClientInstance

        2.2 configure service level configuration
     3. customize builder
     4. return builder





    @Override
    public T build() {
        T builder = createBuilderInstance();
        configureAzureCore(builder);
        configureServiceClient(builder);

        customizeBuilder(builder);
        return builder;
//        setProxy(azureProperties.getProxy());
//        internalSetHttpClientOptions(this.httpClientOptions);
//        return internalBuild();
    }

    private void configureAzureCore(T builder) {
        conigureClientOptions(builder);
        Credential cre = resolveCredentials(properties);
        configureCredentials(builder, cre);
        configureProxy(builder);
    }

    configureCredentials(builder, cre){
        configureKeyCredentials(builder, cre.getKeyCredentials());
        //sas
        //token
    }
    configureKeyCredentials(builder, KeyCredentials){
        builder.setKeyCredentials(keyCredentials);
    }


    resolveCredentials(properties){
     List<String> list =   getSupportCredentialTypes();
     List<CredentialResolver> resolvers = getCredentialsResolver(list);
      Credentials cre = resolvers.findFirst(properties);
    }


    List<String>  getSupportCredentialTypes(){
        return "token", sas , key;
    }

    List<CredentialResolver>    getCredentialsResolver(list){
        List<CredentialResolver> list = applicationContext.getBean().filter(list);
    }
     */


    @Override
    public void setProxy(ProxyProperties proxy) {
        ProxyOptions proxyOptions = convertToProxyOptions(proxy);
        this.httpClientOptions.setProxyOptions(proxyOptions);
    }

    private ProxyOptions convertToProxyOptions(ProxyProperties properties) {
        InetSocketAddress address = new InetSocketAddress(properties.getHostname(), properties.getPort());
        ProxyOptions proxyOptions = new ProxyOptions(getProxyType(properties), address);
        if (StringUtils.hasText(properties.getUsername()) && StringUtils.hasText(properties.getPassword())) {
            proxyOptions.setCredentials(properties.getUsername(), properties.getPassword());
        }
        return proxyOptions;
    }

    private ProxyOptions.Type getProxyType(ProxyProperties properties) {
        // TODO
        return ProxyOptions.Type.HTTP;
    }


}
