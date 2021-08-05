package com.azure.spring.core;

public class SpringConnectionString<T> {

    private T connectionString;

    SpringConnectionString(T connectionString) {
        this.connectionString = connectionString;
    }
}
