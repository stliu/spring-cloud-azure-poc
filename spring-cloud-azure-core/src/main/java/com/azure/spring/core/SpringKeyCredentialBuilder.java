package com.azure.spring.core;

public class SpringKeyCredentialBuilder {

    private String keyCredential;

    SpringKeyCredentialBuilder keyCredential(String keyCredential) {
        this.keyCredential = keyCredential;
        return this;
    }

    public SpringKeyCredential build() {
        return new SpringKeyCredential(keyCredential);
    }
}
