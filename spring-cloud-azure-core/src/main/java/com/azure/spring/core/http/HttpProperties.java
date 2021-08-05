package com.azure.spring.core.http;

import com.azure.spring.core.AzureServiceFeature;

import java.util.List;

public class HttpProperties {
    private List<AzureServiceFeature> features;


    public List<AzureServiceFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<AzureServiceFeature> features) {
        this.features = features;
    }
}
