package com.azure.spring.core.properties.aware;

import com.azure.spring.core.properties.ProxyProperties;

public interface ProxyAware {

    void setProxy(ProxyProperties proxy);
}
