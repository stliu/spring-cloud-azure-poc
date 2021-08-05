package com.azure.spring.autoconfigure.core;

@FunctionalInterface
public interface SpringConnectionStringAware {

    void setSpringConnectionString(SpringConnectionString springConnectionString);
}
