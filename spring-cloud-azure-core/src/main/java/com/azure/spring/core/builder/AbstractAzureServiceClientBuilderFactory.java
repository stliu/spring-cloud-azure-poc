package com.azure.spring.core.builder;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.AzureNamedKeyCredential;
import com.azure.core.credential.AzureSasCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.spring.core.credential.AzureCredential;
import com.azure.spring.core.credential.AzureCredentialResolver;
import com.azure.spring.core.credential.AzureTokenCredentialResolver;
import com.azure.spring.core.properties.AzureProperties;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractAzureServiceClientBuilderFactory<T> implements AzureServiceClientBuilderFactory<T> {

    abstract T createBuilderInstance();

    abstract void configureClientOptions(T builder);

    abstract void configureProxy(T builder);

    abstract void configureService(T builder);

    abstract void customizeBuilder(T builder);

    protected abstract AzureProperties getAzureProperties();

    /**
     1. create a builder instance
     2. configure builder
       2.1 configure azure core level configuration
         2.1.1 configure http client
            getHttpClientInstance
       2.2 configure service level configuration
     3. customize builder
     4. return builder
     */
    public T build() {
        T builder = createBuilderInstance();
        configureCore(builder);
        configureService(builder);
        customizeBuilder(builder);
        return builder;
    }

    public void configureCore(T builder) {
        configureClientOptions(builder);
        configureCredentials(resolveAzureCredential(), builder);
        configureProxy(builder);
    }

    private void configureCredentials(AzureCredential azureCredential, T builder) {

        if (azureCredential.getType() == TokenCredential.class) {
            configureTokenCredential(builder, (TokenCredential) azureCredential.getCredential());
        }

        if (azureCredential.getType() == AzureKeyCredential.class) {
            configureKeyCredential(builder, (AzureKeyCredential) azureCredential.getCredential());
        }

        if (azureCredential.getType() == AzureKeyCredential.class) {
            configureSasCredential(builder, (AzureSasCredential) azureCredential.getCredential());
        }

        if (azureCredential.getType() == AzureKeyCredential.class) {
            configureSasCredential(builder, (AzureSasCredential) azureCredential.getCredential());
        }

        if (azureCredential.getType() == AzureNamedKeyCredential.class) {
            configureNamedKeyCredential(builder, (AzureNamedKeyCredential) azureCredential.getCredential());
        }
    }

    private void configureNamedKeyCredential(T builder, AzureNamedKeyCredential credential) {

    }

    private void configureSasCredential(T builder, AzureSasCredential credential) {

    }

    private void configureKeyCredential(T builder, AzureKeyCredential credential) {

    }

    private void configureTokenCredential(T builder, TokenCredential credential) {

    }


    public List<String> getSupportCredentialTypes(){
        return Arrays.asList("Token");
    }

    public List<AzureCredentialResolver<? extends AzureCredential>> getCredentialsResolvers(List<String> types) {
        // TODO do we need types here
        return Arrays.asList(new AzureTokenCredentialResolver(getAzureProperties()));
    }


    private AzureCredential resolveAzureCredential() {
        List<String> types = getSupportCredentialTypes();
        List<AzureCredentialResolver<? extends AzureCredential>> resolvers = getCredentialsResolvers(types);

        AzureCredential credential = null;
        for (AzureCredentialResolver<? extends AzureCredential> resolver : resolvers) {
            credential = resolver.resolve();
            if (credential != null) {
                break;
            }
        }
        return credential;
    }

    /*
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




    List<CredentialResolver>    getCredentialsResolver(list){
        List<CredentialResolver> list = applicationContext.getBean().filter(list);
    }
     */


}
