package com.azure.spring.core.identify;

public enum AzureServiceFeature {
    TOKEN_CREDENTIAL("token_credential"),
    SAS_TOKEN_CREDENTIAL("sas_token_credential"),
    KEY_CREDENTIAL("key_credential");

    private final String featureType;

    AzureServiceFeature(String featureType) {
        this.featureType = featureType;
    }
}
