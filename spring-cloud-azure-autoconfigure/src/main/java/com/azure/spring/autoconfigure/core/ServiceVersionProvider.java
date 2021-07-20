package com.azure.spring.autoconfigure.core;

import com.azure.core.util.ServiceVersion;

@FunctionalInterface
public interface ServiceVersionProvider<T extends ServiceVersion> {

     T getServiceVersion();

}
