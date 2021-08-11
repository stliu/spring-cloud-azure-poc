package com.azure.spring.core.properties;

import com.azure.spring.core.properties.http.HttpProperties;

public interface HttpPropertiesAware {

    void setHttp(HttpProperties http);
}
