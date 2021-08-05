package com.azure.spring.core;

import java.util.Optional;
import java.util.function.Supplier;

public class SpringConnectionStringBuilder<T> {

    T connectionString;
    Supplier<T> connectionStringSupplier;
    ISpringConnectionStringResolver<T> connectionStringResolver;

    SpringConnectionStringBuilder connectionString(T connectionString) {
        this.connectionString = connectionString;
        return this;
    }

    public SpringConnectionStringBuilder supply(Supplier<T> connectionStringSupplier) {
        this.connectionStringSupplier = connectionStringSupplier;
        return this;
    }

    public SpringConnectionStringBuilder resolve(ISpringConnectionStringResolver connectionStringResolver) {
        this.connectionStringResolver = connectionStringResolver;
        return this;
    }

    public T build() {
        Optional.ofNullable(connectionStringResolver).ifPresent(resolver -> {
            if (this.connectionString != null) {
                this.connectionString = resolver.resolve();
            }
        });
        Optional.ofNullable(connectionStringSupplier).ifPresent(supply -> {
            if (this.connectionString != null) {
                this.connectionString = supply.get();
            }
        });
        return connectionString;
    }
}
