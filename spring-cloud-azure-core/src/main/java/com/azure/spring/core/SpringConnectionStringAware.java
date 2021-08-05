package com.azure.spring.core;

@FunctionalInterface
public interface SpringConnectionStringAware {

    void setSpringConnectionString(ISpringConnectionStringResolver springConnectionString);
}
