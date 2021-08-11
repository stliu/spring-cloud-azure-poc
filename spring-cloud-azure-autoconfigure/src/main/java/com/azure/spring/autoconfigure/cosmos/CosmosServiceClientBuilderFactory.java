package com.azure.spring.autoconfigure.cosmos;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpPipeline;
import com.azure.core.util.ClientOptions;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.core.IAzureKeyCredentialResolver;
import com.azure.spring.core.http.AzureHttpClientBuilderFactory;
import com.azure.spring.core.identify.AzureServiceFeature;
import com.azure.spring.core.properties.ClientOptionsProperties;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;


public class CosmosServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<CosmosClientBuilder>, IAzureKeyCredentialResolver<AzureKeyCredential> {

    private CosmosClientBuilder builder;
    private CosmosProperties cosmosProperties;
    private TokenCredential defaultTokenCredential;

    public CosmosServiceClientBuilderFactory(TokenCredential defaultTokenCredential, CosmosProperties cosmosProperties) {
        this.defaultTokenCredential = defaultTokenCredential;
        this.cosmosProperties = cosmosProperties;
    }

    @Override
    public ClientOptions getClientOptions(ClientOptionsProperties client) {
        return null;
    }

    @Override
    public CosmosClientBuilder build() {
        builder = new CosmosClientBuilder();

        // apply the credential
//        supportFeatures()
//            .stream()
//            .sorted()
//            .forEach(feature -> {
//                switch (feature) {
//                    case KEY_CREDENTIAL:
//                        AzureKeyCredential keyCredential = new AzureSpringKeyCredentialResolver<AzureKeyCredential>().resolve(this);
//                        if (keyCredential != null) {
//                            builder.credential(keyCredential);
//                        }
//                        break;
//                    case TOKEN_CREDENTIAL:
//                        builder.credential(new AzureTokenCredentialResolver().resolve(defaultTokenCredential, cosmosProperties));
//                        break;
//                    default:
//                        throw new IllegalStateException("Cosmos starter does not "
//                            + "support the feature type " + feature + ".");
//            }
//        });
        return builder;
    }

    @Override
    public CosmosClientBuilder builder() {
        return builder;
    }

    @Override
    public AzureKeyCredential resolve() {
        if (StringUtils.hasText(cosmosProperties.getCredential().getKeyOrResourceToken())) {
            return new AzureKeyCredential(cosmosProperties.getCredential().getKeyOrResourceToken());
        }
        return null;
    }

    @Override
    public void setPipeline(HttpPipeline pipeline) {

    }

    @Override
    public List<AzureServiceFeature> supportFeatures() {
        return Arrays.asList(AzureServiceFeature.TOKEN_CREDENTIAL, AzureServiceFeature.KEY_CREDENTIAL);
    }
}
