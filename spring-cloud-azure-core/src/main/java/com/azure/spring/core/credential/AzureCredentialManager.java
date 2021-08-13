package com.azure.spring.core.credential;

import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AzureCredentialManager {

    private Map<AzureCredentialType, Consumer<AzureCredential>> consumerMap = new HashMap<>();

    public void registerConsumer(AzureCredentialType type, Consumer<AzureCredential> consumer) {
        if (consumerMap.containsKey(type)) {
            // TODO logger.warn
        }
        this.consumerMap.put(type, consumer);
    }

    public void configureCredential(@NonNull AzureCredential azureCredential) {
        AzureCredentialType type = azureCredential.getType();
        if (!this.consumerMap.containsKey(type)) {
            // TODO throw exception
        }
        this.consumerMap.get(type).accept(azureCredential);
    }

}
