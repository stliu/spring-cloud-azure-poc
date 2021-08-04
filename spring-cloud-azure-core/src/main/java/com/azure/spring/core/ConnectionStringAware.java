package com.azure.spring.core;

@FunctionalInterface
public interface ConnectionStringAware {

    void setConnectionString(String connectionString);
}
