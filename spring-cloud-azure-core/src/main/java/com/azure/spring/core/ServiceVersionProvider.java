package com.azure.spring.core;

import com.azure.core.util.ServiceVersion;

@FunctionalInterface
public interface ServiceVersionProvider<T extends ServiceVersion> {

     T getServiceVersion();

}
