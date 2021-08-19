package com.azure.spring.autoconfigure.cosmos;

import com.azure.core.credential.TokenCredential;
import com.azure.cosmos.CosmosClientBuilder;


public class CosmosServiceClientBuilderFactory implements com.azure.spring.core.factory.AzureServiceClientBuilderFactory<CosmosClientBuilder> {

    private CosmosClientBuilder builder;
    private CosmosProperties cosmosProperties;
    private TokenCredential defaultTokenCredential;

    public CosmosServiceClientBuilderFactory(TokenCredential defaultTokenCredential, CosmosProperties cosmosProperties) {
        this.defaultTokenCredential = defaultTokenCredential;
        this.cosmosProperties = cosmosProperties;
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

}
