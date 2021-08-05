package com.azure.spring.core;

/**
 * Unify Azure SDK Azure Key Credential usage.
 */
public class SpringKeyCredential<T> {

    private T keyCredential;

    SpringKeyCredential(T keyCredential) {
        this.keyCredential = keyCredential;
    }
}
