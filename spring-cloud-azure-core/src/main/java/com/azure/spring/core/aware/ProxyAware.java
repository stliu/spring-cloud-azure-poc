package com.azure.spring.core.aware;

import com.azure.spring.core.properties.ProxyProperties;

public interface ProxyAware {

    void setProxy(ProxyProperties proxy);
}
