package com.azure.spring.core.aware;

import com.azure.spring.core.properties.http.HttpProperties;

public interface HttpPropertiesAware {

    void setHttp(HttpProperties http);
    HttpProperties getHttp();
}
