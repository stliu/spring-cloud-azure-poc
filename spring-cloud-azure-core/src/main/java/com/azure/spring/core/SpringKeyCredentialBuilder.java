package com.azure.spring.core;

import java.util.Optional;
import java.util.function.Supplier;

public class SpringKeyCredentialBuilder<T> {

    private T keyCredential;
    private IAzureKeyCredentialResolver<T> credentialResolver;
    Supplier<T> keyCredentialSupplier;

    public SpringKeyCredentialBuilder resolve(IAzureKeyCredentialResolver<T> credentialResolver) {
        this.credentialResolver = credentialResolver;
        return this;
    }

    public SpringKeyCredentialBuilder supply(Supplier<T> keyCredentialSupplier) {
        this.keyCredentialSupplier = keyCredentialSupplier;
        return this;
    }

    public T build() {
        Optional.ofNullable(credentialResolver).ifPresent(resolver -> {
            if (this.keyCredential != null) {
                this.keyCredential = resolver.resolve();
            }
        });
        Optional.ofNullable(keyCredentialSupplier).ifPresent(supply -> {
            if (this.keyCredential != null) {
                this.keyCredential = supply.get();
            }
        });
        return keyCredential;
    }
}
