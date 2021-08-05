package com.azure.spring.autoconfigure.cosmos;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.TokenCredential;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.core.AzureTokenCredentialResolver;
import com.azure.spring.core.IAzureKeyCredentialResolver;
import com.azure.spring.core.AzureSpringKeyCredentialResolver;
import com.azure.spring.core.http.AzureHttpClientBuilderFactory;
import com.azure.spring.core.identify.AzureServiceFeature;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;


public class CosmosServiceClientBuilderFactory implements
    AzureHttpClientBuilderFactory<CosmosClientBuilder>, IAzureKeyCredentialResolver<AzureKeyCredential> {

    private CosmosClientBuilder delegated;
    private CosmosProperties cosmosProperties;
    private TokenCredential defaultTokenCredential;

    public CosmosServiceClientBuilderFactory(TokenCredential defaultTokenCredential, CosmosProperties cosmosProperties) {
        this.defaultTokenCredential = defaultTokenCredential;
        this.cosmosProperties = cosmosProperties;
    }

    @Override
    public CosmosClientBuilder build() {
        delegated = new CosmosClientBuilder();

        // apply the credential
        supportFeatures().forEach(feature -> {
            switch (feature) {
                case KEY_CREDENTIAL:
                    AzureKeyCredential keyCredential = new AzureSpringKeyCredentialResolver<AzureKeyCredential>().resolve(this);
                    if (keyCredential != null) {
                        delegated.credential(keyCredential);
                    }
                    break;
                case TOKEN_CREDENTIAL:
                    delegated.credential(new AzureTokenCredentialResolver().resolve(defaultTokenCredential, cosmosProperties));
                    break;
                default:
                    throw new IllegalStateException("Cosmos starter does not "
                        + "support the feature type" + feature + ".");
            }
        });
        return delegated;
    }

    @Override
    public com.azure.core.credential.AzureKeyCredential resolve() {
        if (StringUtils.hasText(cosmosProperties.getKey())) {
            return new com.azure.core.credential.AzureKeyCredential(cosmosProperties.getKey());
        }
        return null;
    }

    @Override
    public List<AzureServiceFeature> supportFeatures() {
        return Arrays.asList(AzureServiceFeature.TOKEN_CREDENTIAL);
    }
}
