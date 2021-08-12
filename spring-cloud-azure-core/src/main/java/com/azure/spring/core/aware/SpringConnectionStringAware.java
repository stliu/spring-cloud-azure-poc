package com.azure.spring.core.aware;

import com.azure.spring.core.ISpringConnectionStringResolver;

@FunctionalInterface
public interface SpringConnectionStringAware {

    void setSpringConnectionString(ISpringConnectionStringResolver springConnectionString);
}
