package com.azure.spring.core.http;

import com.azure.spring.core.identify.AzureServiceFeature;

import java.util.List;

public class HttpProperties {

    private boolean enabledCustomizer;

    private List<AzureServiceFeature> features;


    public boolean getEnabledCustomizer() {
        return enabledCustomizer;
    }

    public void setEnabledCustomizer(boolean enabledCustomizer) {
        this.enabledCustomizer = enabledCustomizer;
    }

    public List<AzureServiceFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<AzureServiceFeature> features) {
        this.features = features;
    }
}
