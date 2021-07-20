package com.azure.spring.autoconfigure.core;

@FunctionalInterface
public interface ConnectionStringAware {

    void setConnectionString(String connectionString);
}