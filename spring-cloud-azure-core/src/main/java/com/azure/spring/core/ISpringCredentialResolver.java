package com.azure.spring.core;

public interface ISpringCredentialResolver<T> {
    T resolve();
}
