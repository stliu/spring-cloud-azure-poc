package com.azure.spring.core;

public class SpringConnectionStringBuilder {

    String connectionString;

    SpringConnectionStringBuilder connectionString(String connectionString) {
        this.connectionString = connectionString;
        return this;
    }

    public SpringConnectionString build() {
        return new SpringConnectionString(connectionString);
    }
}
